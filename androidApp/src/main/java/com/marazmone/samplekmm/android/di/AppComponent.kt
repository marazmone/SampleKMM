package com.marazmone.samplekmm.android.di

import com.marazmone.samplekmm.android.App
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent: AndroidInjector<App?> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App?>
}