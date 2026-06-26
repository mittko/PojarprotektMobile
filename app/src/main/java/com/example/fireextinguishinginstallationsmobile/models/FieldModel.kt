package com.example.fireextinguishinginstallationsmobile.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fireextinguishinginstallationsmobile.interfaces.IModel

data class FieldModel(
    override val subTitle: String,
    override val position: Int
) : IModel {

    var data by mutableStateOf("")

}