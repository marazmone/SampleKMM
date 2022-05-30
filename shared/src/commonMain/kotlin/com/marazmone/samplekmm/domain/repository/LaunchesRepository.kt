package com.marazmone.samplekmm.domain.repository

import com.marazmone.samplekmm.data.model.response.RocketLaunchResponse
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import com.marazmone.samplekmm.utils.ResultWrapper

interface LaunchesRepository {

    suspend fun getAll(): ResultWrapper<List<RocketLaunchesModel>>
}