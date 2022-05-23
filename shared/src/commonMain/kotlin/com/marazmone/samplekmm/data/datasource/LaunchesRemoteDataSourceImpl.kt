package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private const val LAUNCHES_ENDPOINT = "https://api.spacexdata.com/v3/launches"

internal class LaunchesRemoteDataSourceImpl(
    private val api: HttpClient
): LaunchesRemoteDataSource {

    override suspend fun getAll(): List<RocketLaunchEntitys> = api.get(LAUNCHES_ENDPOINT).body()
}