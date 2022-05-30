package com.marazmone.samplekmm.data.mapper

import com.marazmone.samplekmm.data.mapper.base.Mapper
import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel

class RocketLaunchesEntityToModelMapper : Mapper<RocketLaunchEntity, RocketLaunchesModel> {

    override fun map(source: RocketLaunchEntity): RocketLaunchesModel =
        RocketLaunchesModel(
            name = source.missionName,
            year = source.launchYear,
            status = source.launchSuccess,
            details = source.details
        )
}