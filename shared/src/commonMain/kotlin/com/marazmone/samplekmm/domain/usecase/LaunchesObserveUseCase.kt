package com.marazmone.samplekmm.domain.usecase

import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

class LaunchesObserveUseCase(
    private val repository: LaunchesRepository
) {

    suspend fun execute(): Flow<List<RocketLaunchesModel>> = repository.observeAll()
}