package com.marazmone.samplekmm.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketResponse(
    @SerialName("rocket_id")
    val id: String? = null,
    @SerialName("rocket_name")
    val name: String? = null,
    @SerialName("rocket_type")
    val type: String? = null,
)