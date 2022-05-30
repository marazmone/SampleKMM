package com.marazmone.samplekmm.data.repository

import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSource
import com.marazmone.samplekmm.data.mapper.RocketLaunchesEntityToModelMapper
import com.marazmone.samplekmm.data.mapper.base.Mapper
import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.utils.ResultWrapper

internal class LaunchesRepositoryImpl(
    private val cache: LaunchesCacheDataSource,
    private val remote: LaunchesRemoteDataSource,
) : LaunchesRepository {

    override suspend fun getAll(): ResultWrapper<List<RocketLaunchesModel>> {
        val result = remote.getAll()
        return if (result.isSuccess()) {
            val entity = RocketLaunchResponse.list(result.asSuccess().value)
            cache.save(entity)
            val model = RocketLaunchEntity.list(cache.getAll())
            return ResultWrapper.Success(model)
        } else {
            result.asError()
        }
    }
}