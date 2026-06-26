package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonFieldModel(
    val subTitle: String, val position: Int,
    val data: String
) : JsonModel("field")