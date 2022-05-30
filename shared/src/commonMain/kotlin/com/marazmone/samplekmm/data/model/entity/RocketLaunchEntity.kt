package com.marazmone.samplekmm.data.model.entity

import com.marazmone.samplekmm.data.mapper.base.Mapper
import com.marazmone.samplekmm.domain.model.RocketLaunchesModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class RocketLaunchEntity : RealmObject {

    @PrimaryKey
    var id: Int = 0

    var launchSuccess: Boolean = false

    var details: String = ""

    var launchYear: Int = 0

    var missionName: String = ""

    companion object : Mapper<RocketLaunchEntity, RocketLaunchesModel> {
        override fun map(source: RocketLaunchEntity): RocketLaunchesModel =
            RocketLaunchesModel(
                name = source.missionName,
                year = source.launchYear,
                status = source.launchSuccess,
                details = source.details
            )
    }
}