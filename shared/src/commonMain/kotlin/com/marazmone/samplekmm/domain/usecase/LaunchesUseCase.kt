package com.marazmone.samplekmm.domain.usecase

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.domain.repository.LaunchesRepository

class LaunchesUseCase(
    private val repository: LaunchesRepository
) {

    @Throws(Exception::class)
    suspend fun execute(): List<RocketLaunchEntitys> = repository.getAll()
}