package com.marazmone.samplekmm.android

import com.marazmone.samplekmm.android.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out App?> =
        DaggerAppComponent.factory().create(this)
}