package com.marazmone.samplekmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RocketLaunchEntitys {
    @SerialName("flight_number")
    var flightNumber: Int = 0

    @SerialName("mission_name")
    var missionName: String = ""

    @SerialName("launch_year")
    var launchYear: Int = 0

    @SerialName("launch_date_utc")
    var launchDateUTC: String = ""

    @SerialName("rocket")
    var rocketEntity: RocketEntity? = RocketEntity()

    @SerialName("details")
    var details: String? = ""

    @SerialName("launch_success")
    var launchSuccess: Boolean? = false

    @SerialName("links")
    var linksEntity: LinksEntity? = LinksEntity()
}