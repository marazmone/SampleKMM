package com.marazmone.samplekmm.domain.usecase

import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.utils.ResultWrapper

class LaunchesUpdateUseCase(
    private val repository: LaunchesRepository
) {

    suspend fun execute(): ResultWrapper<Unit> = repository.updateAll()
}