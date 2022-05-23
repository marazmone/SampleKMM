package com.marazmone.samplekmm.android.presentation.base.action

sealed class ActionState {

    class LOADING(val isRemote: Boolean) : ActionState()

    object EMPTY : ActionState()

    object SUCCESS : ActionState()

    object ERROR : ActionState()
}