package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.utils.ResultWrapper

interface LaunchesCacheDataSource {

    suspend fun getAll(): List<RocketLaunchEntitys>

    suspend fun save(launches: List<RocketLaunchEntitys>)

    suspend fun clear()
}

interface LaunchesRemoteDataSource {

    suspend fun getAll(): ResultWrapper<List<RocketLaunchEntitys>>
}