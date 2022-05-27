package com.marazmone.samplekmm.android

import android.app.Application
import com.marazmone.samplekmm.di.koinInit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        koinInit()
    }
}