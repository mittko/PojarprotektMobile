package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonDropDownModel(
    val subTitle: String, val position: Int,
    val data: String
) : JsonModel("dropdown")