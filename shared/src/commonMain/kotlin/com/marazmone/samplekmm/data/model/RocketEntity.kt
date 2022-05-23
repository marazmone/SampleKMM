package com.marazmone.samplekmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RocketEntity {
    @SerialName("rocket_id")
    var id: String = ""

    @SerialName("rocket_name")
    var name: String = ""

    @SerialName("rocket_type")
    var type: String = ""
}