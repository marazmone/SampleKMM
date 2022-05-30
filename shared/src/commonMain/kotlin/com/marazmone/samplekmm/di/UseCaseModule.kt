package com.marazmone.samplekmm.di

import com.marazmone.samplekmm.domain.usecase.LaunchesObserveUseCase
import com.marazmone.samplekmm.domain.usecase.LaunchesUpdateUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::LaunchesUpdateUseCase)
    factoryOf(::LaunchesObserveUseCase)
}