package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCheckedDataModelThree(
    val subTitle: String, val position: Int, val checked: Boolean, val unchecked: Boolean,
    val data: String, val previousMeasurement: String, val currentMeasurement: String
) : JsonModel("checkable measure")