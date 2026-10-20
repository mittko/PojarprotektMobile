package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonDropDownModel(
    val subTitle: String,
    val data: String,
    val innerOptions : String?
) : JsonModel("dropdown")