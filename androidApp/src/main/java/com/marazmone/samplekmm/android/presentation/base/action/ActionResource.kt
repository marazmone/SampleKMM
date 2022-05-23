package com.marazmone.samplekmm.android.presentation.base.action

data class ActionResource<out T>(
    val state: ActionState,
    val data: T? = null,
    val error: ActionError? = null,
) {

    companion object {
        fun <T> success(data: T): ActionResource<T> {
            return ActionResource(state = ActionState.SUCCESS, data, error = null)
        }

        fun <T> empty(): ActionResource<T> {
            return ActionResource(state = ActionState.EMPTY)
        }

        fun <T> error(
            data: T? = null,
            errorMessage: String = "ERROR",
            code: Int = 0
        ): ActionResource<T> {
            return ActionResource(state = ActionState.ERROR, data, error = ActionError(errorMessage, code))
        }
    }
}

data class ActionError(
    val message: String?,
    val code: Int,
)

fun <T> ActionResource<T>.doOnResult(
    progressDelegate: ProgressDelegate? = null,
    onLoading: ((isRemote: Boolean) -> Unit)? = null,
    onSuccess: ((T) -> Unit)? = null,
    onError: ((errorDescription: String, data: T?) -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
    onEmpty: (() -> Unit)? = null
) {
    when (state) {
        is ActionState.LOADING -> {
            onLoading?.invoke(state.isRemote)
            progressDelegate?.show()
        }
        is ActionState.SUCCESS -> {
            data?.also {
                onSuccess?.invoke(it)
            } ?: onError?.invoke("ERROR", data)
            progressDelegate?.hide()
            onFinish?.invoke()
        }
        is ActionState.ERROR -> {
            val errorTextMessage = error?.message ?: "ERROR"
            onError?.invoke(errorTextMessage, data)
            progressDelegate?.hide()
            onFinish?.invoke()
        }
        ActionState.EMPTY -> {
            onEmpty?.invoke()
            onFinish?.invoke()
            progressDelegate?.hide()
        }
    }
}