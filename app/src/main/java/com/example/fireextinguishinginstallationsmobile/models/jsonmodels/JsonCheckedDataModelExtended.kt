package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCheckedDataModelExtended(
    val subTitle: String,  val checked: Boolean, val unchecked: Boolean,
    val data: String, val pressure: String, val lastPressure: String,
    val fabNum: String, val hidrostatMeasurementDate: String, val device: String
) : JsonModel("checkable extended")


