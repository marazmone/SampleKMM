package com.marazmone.samplekmm.data.repository

import com.marazmone.samplekmm.data.datasource.LaunchesCacheDataSource
import com.marazmone.samplekmm.data.datasource.LaunchesRemoteDataSource
import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.domain.repository.LaunchesRepository

internal class LaunchesRepositoryImpl(
    private val cache: LaunchesCacheDataSource,
    private val remote: LaunchesRemoteDataSource,
) : LaunchesRepository {

    override suspend fun getAll(): List<RocketLaunchEntitys> {
        return remote.getAll()
    }
}