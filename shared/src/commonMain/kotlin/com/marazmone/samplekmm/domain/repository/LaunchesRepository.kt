package com.marazmone.samplekmm.domain.repository

import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    suspend fun updateAll(): ResultWrapper<Unit>

    suspend fun observeAll(): Flow<List<RocketLaunchesModel>>

    suspend fun deleteById(id: Int)
}