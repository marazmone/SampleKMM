package com.marazmone.samplekmm.di

import com.marazmone.samplekmm.data.repository.LaunchesRepositoryImpl
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::LaunchesRepositoryImpl) { bind<LaunchesRepository>()}
}