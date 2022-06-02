package com.marazmone.samplekmm.domain.model

data class RocketLaunchesModel(
    val id: Int,
    val name: String,
    val year: Int,
    val status: Boolean,
    val details: String,
)