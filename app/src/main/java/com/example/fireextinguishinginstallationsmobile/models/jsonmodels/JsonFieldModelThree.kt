package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonFieldModelThree(
    val subTitle: String, val position: Int,
    val data: String, val pressure: String, val oldPressure: String
) : JsonModel("field data three")