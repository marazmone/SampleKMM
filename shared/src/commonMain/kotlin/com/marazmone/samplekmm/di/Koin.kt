package com.marazmone.samplekmm.di

import org.koin.core.context.startKoin

fun koinInit() {
    startKoin {
        modules(
            repositoryModule,
            datasourceModule,
            useCaseModule,
        )
    }
}