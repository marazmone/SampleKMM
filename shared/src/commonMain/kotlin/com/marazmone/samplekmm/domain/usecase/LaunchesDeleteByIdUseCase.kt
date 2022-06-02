package com.marazmone.samplekmm.domain.usecase

import com.marazmone.samplekmm.domain.repository.LaunchesRepository

class LaunchesDeleteByIdUseCase(
    private val repository: LaunchesRepository
) {

    suspend fun execute(id: Int) = repository.deleteById(id)
}