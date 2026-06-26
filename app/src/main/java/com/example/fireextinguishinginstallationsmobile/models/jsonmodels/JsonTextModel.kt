package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonTextModel(
    val subTitle: String, val position: Int,
    val data: String
) : JsonModel("text")