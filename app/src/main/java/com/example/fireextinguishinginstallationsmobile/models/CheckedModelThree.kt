package com.example.fireextinguishinginstallationsmobile.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fireextinguishinginstallationsmobile.interfaces.ICheckable

@Stable
data class CheckedModelThree(
    override val subTitle: String
) : ICheckable {


    override var checked by mutableStateOf(true)

    override var unchecked by mutableStateOf(false)

    var data by mutableStateOf("")

    var previousMeasurement by mutableStateOf("")

    var currentMeasurement by mutableStateOf("")
}