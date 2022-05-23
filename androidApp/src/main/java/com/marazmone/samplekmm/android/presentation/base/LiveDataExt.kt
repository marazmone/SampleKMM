package com.marazmone.samplekmm.android.presentation.base

import androidx.lifecycle.MutableLiveData
import com.marazmone.samplekmm.android.presentation.base.action.ActionError
import com.marazmone.samplekmm.android.presentation.base.action.ActionResource
import com.marazmone.samplekmm.android.presentation.base.action.ActionState

fun <T> MutableLiveData<ActionResource<T>>.setSuccess(data: T? = null) {
    value = ActionResource(ActionState.SUCCESS, data)
}

fun <T> MutableLiveData<ActionResource<T>>.setLoading(isRemote: Boolean = true) {
    value = ActionResource(ActionState.LOADING(isRemote), value?.data)
}

fun <T> MutableLiveData<ActionResource<T>>.setError(message: String? = null, code: Int = 0) {
    value = ActionResource(ActionState.ERROR, value?.data, ActionError(message, code))
}

fun <T> MutableLiveData<ActionResource<T>>.setEmpty() {
    value = ActionResource(ActionState.EMPTY)
}
