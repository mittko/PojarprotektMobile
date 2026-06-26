package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonCheckedDataModelTwo(
    val subTitle: String, val position: Int, val checked: Boolean, val unchecked: Boolean,
    val data: String, val count: String
) : JsonModel("checkable count")