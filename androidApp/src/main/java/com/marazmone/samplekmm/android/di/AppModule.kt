package com.marazmone.samplekmm.android.di

import com.marazmone.samplekmm.android.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class AppModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}