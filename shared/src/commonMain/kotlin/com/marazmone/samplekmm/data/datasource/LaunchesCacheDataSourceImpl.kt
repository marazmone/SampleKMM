package com.marazmone.samplekmm.data.datasource

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys

internal class LaunchesCacheDataSourceImpl(
    private val database: Any
) : LaunchesCacheDataSource {

    override suspend fun getAll(): List<RocketLaunchEntitys> {
        throw Throwable("need implement database")
    }

    override suspend fun save(launches: List<RocketLaunchEntitys>) {
        throw Throwable("need implement database")
    }

    override suspend fun clear() {
        throw Throwable("need implement database")
    }
}