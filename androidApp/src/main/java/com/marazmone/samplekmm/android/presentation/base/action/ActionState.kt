package com.marazmone.samplekmm.android.presentation.base.action

sealed class ActionState {

    class LOADING(val isRemote: Boolean) : ActionState() {
        override fun toString(): String = "LOADING $isRemote"
    }

    object EMPTY : ActionState() {
        override fun toString(): String = "EMPTY"
    }

    object SUCCESS : ActionState() {
        override fun toString(): String = "SUCCESS"
    }

    object ERROR : ActionState() {
        override fun toString(): String = "ERROR"
    }
}