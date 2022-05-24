package com.marazmone.samplekmm.presentation.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dev.icerock.moko.mvvm.viewmodel.ViewModel

abstract class BaseActivity<VM : ViewModel>(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {

    protected lateinit var viewModel: VM

    protected abstract val viewModelClass: Class<VM>

    protected abstract fun viewModelFactory(): ViewModelProvider.Factory

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val provider = ViewModelProvider(this, viewModelFactory())
        viewModel = provider[viewModelClass]
    }
}
