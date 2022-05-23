package com.marazmone.samplekmm.android.presentation.base

import android.util.Log
import com.marazmone.samplekmm.android.presentation.base.action.ActionError
import com.marazmone.samplekmm.android.presentation.base.action.ActionResource
import com.marazmone.samplekmm.android.presentation.base.action.ActionState
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<ActionResource<T>>.setSuccess(data: T? = null) {
    val action = ActionResource(ActionState.SUCCESS, data)
    Log.d("ACTION_RESOURCE", "STATE: ${action.state}")
    value = action
}

fun <T> MutableStateFlow<ActionResource<T>>.setLoading(isRemote: Boolean = true) {
    val action = ActionResource(ActionState.LOADING(isRemote), value.data)
    Log.d("ACTION_RESOURCE", "STATE: ${action.state}")
    value = action
}

fun <T> MutableStateFlow<ActionResource<T>>.setError(message: String? = null, code: Int = 0) {
    val action = ActionResource(ActionState.ERROR, value.data, ActionError(message, code))
    Log.d("ACTION_RESOURCE", "STATE: ${action.state}")
    value = action
}

fun <T> MutableStateFlow<ActionResource<T>>.setEmpty() {
    val action = ActionResource<T>(ActionState.EMPTY)
    Log.d("ACTION_RESOURCE", "STATE: ${action.state}")
    value = action
}
