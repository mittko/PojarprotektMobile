package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCheckedModel(
    val subTitle: String, val checked: Boolean, val unchecked: Boolean,
    val data: String
) : JsonModel("checkable")