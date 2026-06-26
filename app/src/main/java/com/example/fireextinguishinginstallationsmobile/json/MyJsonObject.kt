package com.example.fireextinguishinginstallationsmobile.json

import com.example.fireextinguishinginstallationsmobile.models.jsonmodels.JsonModel

data class MyJsonObject(
    val objectId: String,
    val operatorName: String,
    val contractDate: String,
    val defaultDocument: String,
    val mutableMap: MutableMap<String, ArrayList<JsonModel>>
)