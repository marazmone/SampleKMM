package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.utils.ResultWrapper

interface LaunchesCacheDataSource {

    suspend fun getAll(): List<RocketLaunchEntity>

    suspend fun save(launches: List<RocketLaunchEntity>)

    suspend fun clear()
}

interface LaunchesRemoteDataSource {

    suspend fun getAll(): ResultWrapper<List<RocketLaunchResponse>>
}