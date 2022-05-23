package com.marazmone.samplekmm.domain.repository

import com.marazmone.samplekmm.data.model.RocketLaunchEntitys

interface LaunchesRepository {

    suspend fun getAll():  List<RocketLaunchEntitys>
}