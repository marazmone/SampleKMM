package com.marazmone.samplekmm.di

import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSourceImpl
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSourceImpl
import com.marazmone.samplekmm.data.repository.LaunchesRepositoryImpl
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.domain.usecase.LaunchesUseCase
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object LaunchesModule {

    private val apiClient = HttpClient {
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

    private val cacheDataSource: LaunchesCacheDataSource = LaunchesCacheDataSourceImpl(Any())

    private val remoteDataSource: LaunchesRemoteDataSource = LaunchesRemoteDataSourceImpl(apiClient)

    private val repository: LaunchesRepository = LaunchesRepositoryImpl(
        cache = cacheDataSource,
        remote = remoteDataSource,
    )

    val launchesUseCase: LaunchesUseCase = LaunchesUseCase(repository)
}