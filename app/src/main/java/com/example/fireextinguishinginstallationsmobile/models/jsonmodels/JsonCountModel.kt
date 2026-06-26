package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCountModel(
    val subTitle: String, val position: Int,
    val data: String
) : JsonModel("count")