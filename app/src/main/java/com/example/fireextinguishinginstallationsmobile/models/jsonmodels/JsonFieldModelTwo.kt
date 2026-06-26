package com.example.fireextinguishinginstallationsmobile.models.jsonmodels

data class JsonFieldModelTwo(
    val subTitle: String, val position: Int,
    val data: String, val dataTwo: String
) : JsonModel("field data two")