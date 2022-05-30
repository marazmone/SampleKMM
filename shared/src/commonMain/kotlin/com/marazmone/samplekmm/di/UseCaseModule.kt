package com.marazmone.samplekmm.di

import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::LaunchesUseCase)
}