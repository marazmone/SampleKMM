package com.marazmone.samplekmm.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class LinksEntity {
    @SerialName("mission_patch")
    var missionPatchUrl: String? = ""

    @SerialName("article_link")
    var articleUrl: String? = ""
}
