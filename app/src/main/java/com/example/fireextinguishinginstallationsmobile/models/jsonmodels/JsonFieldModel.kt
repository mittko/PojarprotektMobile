package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonFieldModel(
    val subTitle: String,
    var data: String
) : JsonModel("field")