package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCheckedDataModelExtended(
    val subTitle: String,  val checked: Boolean, val unchecked: Boolean,
    val data: String, val pressure: Float, val lastPressure: Float,
    val fabNum: String, val hidrostatMeasurementDate: String, val device: String
) : JsonModel("checkable extended")


