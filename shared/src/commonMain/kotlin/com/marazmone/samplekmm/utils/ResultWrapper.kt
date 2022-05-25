package com.marazmone.samplekmm.utils

import com.marazmone.samplekmm.utils.ResultWrapper.Success
import com.marazmone.samplekmm.utils.ResultWrapper.Error
import com.marazmone.samplekmm.utils.ResultWrapper.Error.CancellationError
import com.marazmone.samplekmm.utils.ResultWrapper.Error.ClientError
import com.marazmone.samplekmm.utils.ResultWrapper.Error.NetworkError
import com.marazmone.samplekmm.utils.ResultWrapper.Error.ServerError
import com.marazmone.samplekmm.utils.ResultWrapper.Error.UnknownError
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException

private const val UNKNOWN_ERROR = "Unknown error"

sealed class ResultWrapper<out T> {

    data class Success<out T>(val value: T) : ResultWrapper<T>()
    sealed class Error(val msg: String) : ResultWrapper<Nothing>() {
        data class ServerError(val httpCode: String, val message: String) : Error(message)
        data class ClientError(val httpCode: String, val errorCode: String, val message: String) :
            Error(message)

        data class UnknownError(val message: String) : Error(message)
        data class NetworkError(val message: String) : Error(message)

        object CancellationError : Error("StandaloneCoroutine was cancelled")
    }

    fun doOnResult(
        onSuccess: ((T) -> Unit)? = null,
        onServerError: ((error: ServerError) -> Unit)? = null,
        onClientError: ((error: ClientError) -> Unit)? = null,
        onNetworkError: ((error: NetworkError) -> Unit)? = null,
        onUnknownError: ((error: UnknownError) -> Unit)? = null,
        onError: ((error: Error) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
    ): ResultWrapper<T> {
        if (this is Success) {
            onSuccess?.invoke(value)
        } else if (this is ServerError && onServerError != null) {
            onServerError.invoke(this)
        } else if (this is ClientError && onClientError != null) {
            onClientError.invoke(this)
        } else if (this is NetworkError && onNetworkError != null) {
            onNetworkError.invoke(this)
        } else if (this is UnknownError && onUnknownError != null) {
            onUnknownError.invoke(this)
        } else if (this is CancellationError) {
            // nothing
        } else if (this is Error) {
            onError?.invoke(this)
        } else {
            throw IllegalStateException("Unknown state in ResultWrapper: $this")
        }
        onFinish?.invoke()
        return this
    }

    fun isSuccess(): Boolean = this is Success

    fun asSuccess(): Success<T> = this as Success<T>

    fun isError(): Boolean = this is Error

    fun asError(): Error = this as Error

    companion object ErrorHandler {

        @Suppress("VARIABLE_IN_SINGLETON_WITHOUT_THREAD_LOCAL")
        var errorLogDispatcher: ((HttpErrorLog) -> Unit)? = null
    }
}

suspend inline fun <reified T> safeApiCall(call: () -> HttpResponse): ResultWrapper<T> {
    var url = ""
    return try {
        val response = call.invoke()
        val code = response.status.value
        url = response.request.url.toString()
        when {
            response.status.isSuccess() -> {
                Success(response.body())
            }
            else -> {
                val httpCode = code.toString()
                val errorMessage = httpCode
                    .plus(": ${response.status.description}")

                if (code in 400..499) {
                    ClientError(httpCode, httpCode, errorMessage)
                } else {
                    ServerError(httpCode, errorMessage)
                }
            }
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        when (throwable) {
            is CancellationException -> CancellationError
//            is SSLHandshakeException -> {
//                ResultWrapper.errorLogDispatcher?.invoke(HttpErrorLog(url = url, error = throwable))
//                UnknownError(
//                    "Сертифікат для даного сервера не є дійсним! " +
//                            "Це може бути небезпечним для Вашої конфіденційної інформації!"
//                )
//            }
            else -> {
                ResultWrapper.errorLogDispatcher?.invoke(HttpErrorLog(url = url, error = throwable))
                UnknownError("Щось пішло не так, спробуйте\nповторити операцію пізніше")
            }
        }
    }
}

inline fun <SOURCE, RESULT> ResultWrapper<SOURCE>.flatMap(
    mapper: (SOURCE) -> ResultWrapper<RESULT>
): ResultWrapper<RESULT> {
    return when (this) {
        is Success -> mapper(value)
        is Error -> this
    }
}

inline fun <SOURCE, RESULT> ResultWrapper<SOURCE>.map(mapper: (SOURCE) -> RESULT): ResultWrapper<RESULT> {
    return when (this) {
        is Success -> Success(mapper(value))
        is Error -> this
    }
}

data class HttpErrorLog(
    val error: Throwable,
    val url: String,
) : Throwable(error)