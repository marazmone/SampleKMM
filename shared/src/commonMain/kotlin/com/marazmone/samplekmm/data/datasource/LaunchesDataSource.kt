package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface LaunchesCacheDataSource {

    suspend fun getAll(): List<RocketLaunchEntity>

    suspend fun save(launches: List<RocketLaunchEntity>)

    suspend fun observeAll(): Flow<List<RocketLaunchEntity>>

    suspend fun clear()

    suspend fun deleteById(id: Int)
}

interface LaunchesRemoteDataSource {

    suspend fun getAll(): ResultWrapper<List<RocketLaunchResponse>>
}