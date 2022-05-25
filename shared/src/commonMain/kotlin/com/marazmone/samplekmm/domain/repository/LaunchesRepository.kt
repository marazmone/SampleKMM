package com.marazmone.samplekmm.domain.repository

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys
import com.marazmone.samplekmm.utils.ResultWrapper

interface LaunchesRepository {

    suspend fun getAll(): ResultWrapper<List<RocketLaunchEntitys>>
}