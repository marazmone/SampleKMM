package com.marazmone.samplekmm.android.di

import androidx.lifecycle.ViewModel
import com.marazmone.samplekmm.android.di.annotation.ViewModelKey
import com.marazmone.samplekmm.android.presentation.MainViewModel
import com.marazmone.samplekmm.di.LaunchesModule
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewViewModel: MainViewModel): ViewModel

    companion object {

        @Provides
        fun provideLaunchesUseCase(): LaunchesUseCase = LaunchesModule.launchesUseCase
    }
}