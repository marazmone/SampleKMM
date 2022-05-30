package com.marazmone.samplekmm.domain.usecase

import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.domain.repository.LaunchesRepository
import com.marazmone.samplekmm.utils.ResultWrapper

class LaunchesUseCase(
    private val repository: LaunchesRepository
) {

    suspend fun execute(): ResultWrapper<List<RocketLaunchesModel>> = repository.getAll()
}