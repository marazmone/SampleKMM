package com.marazmone.samplekmm.data.model.response

import com.marazmone.samplekmm.data.mapper.base.Mapper
import com.marazmone.samplekmm.data.model.entity.RocketLaunchEntity
import com.marazmone.samplekmm.utils.orEmpty
import com.marazmone.samplekmm.utils.orFalse
import com.marazmone.samplekmm.utils.orZero
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketLaunchResponse(
    @SerialName("launch_year")
    val launchYear: Int? = null,
    @SerialName("details")
    val details: String? = null,
    @SerialName("launch_success")
    val launchSuccess: Boolean? = null,
    @SerialName("flight_number")
    val flightNumber: Int? = null,
    @SerialName("mission_name")
    val missionName: String? = null,
    @SerialName("launch_date_utc")
    val launchDateUTC: String? = null,
    @SerialName("rocket")
    val rocket: RocketResponse? = null,
    @SerialName("links")
    val links: LinksResponse? = null
) {

    companion object : Mapper<RocketLaunchResponse, RocketLaunchEntity> {
        override fun map(source: RocketLaunchResponse): RocketLaunchEntity =
            RocketLaunchEntity().apply {
                id = source.flightNumber.orZero
                details = source.details.orEmpty
                launchYear = source.launchYear.orZero
                missionName = source.missionName.orEmpty
                launchSuccess = source.launchSuccess.orFalse
            }
    }
}