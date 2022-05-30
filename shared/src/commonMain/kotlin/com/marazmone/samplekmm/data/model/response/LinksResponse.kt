package com.marazmone.samplekmm.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksResponse(
    @SerialName("mission_patch")
    val missionPatchUrl: String? = null,
    @SerialName("article_link")
    val articleUrl: String? = null
)
