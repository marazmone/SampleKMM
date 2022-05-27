package com.marazmone.samplekmm.di

import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSourceImpl
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSourceImpl
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val datasourceModule = module {
    singleOf(::LaunchesRemoteDataSourceImpl) { bind<LaunchesRemoteDataSource>() }
    singleOf(::LaunchesCacheDataSourceImpl) { bind<LaunchesCacheDataSource>() }

    single {
        HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}
