package com.example.fireextinguishinginstallationsmobile.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fireextinguishinginstallationsmobile.interfaces.IModel

@Stable
data class DropDownModel(
    override val subTitle: String
) : IModel {


    var data by mutableStateOf("")
}