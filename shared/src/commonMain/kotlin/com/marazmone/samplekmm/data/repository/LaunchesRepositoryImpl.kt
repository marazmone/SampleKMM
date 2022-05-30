package com.marazmone.samplekmm.data.repository

import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSource
import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.utils.ResultWrapper
import com.marazmone.samplekmm.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LaunchesRepositoryImpl(
    private val cache: LaunchesCacheDataSource,
    private val remote: LaunchesRemoteDataSource,
) : LaunchesRepository {

    override suspend fun updateAll(): ResultWrapper<Unit> {
        val result = remote.getAll()
        if (result.isSuccess()) {
            val entity = RocketLaunchResponse.list(result.asSuccess().value)
            cache.save(entity)
        }
        return result.map {  }
    }

    override suspend fun observeAll(): Flow<List<RocketLaunchesModel>> =
        cache.observeAll().map { RocketLaunchEntity.list(it) }
}