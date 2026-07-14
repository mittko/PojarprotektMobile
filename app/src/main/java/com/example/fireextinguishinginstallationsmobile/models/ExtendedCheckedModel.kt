package com.example.fireextinguishinginstallationsmobile.models

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fireextinguishinginstallationsmobile.interfaces.ICheckable

@Stable
data class ExtendedCheckedModel(
    override val subTitle: String
) : ICheckable {


    override var checked by mutableStateOf(true)

    override var unchecked by mutableStateOf(false)

    var data by mutableStateOf("")

    var pressure by mutableFloatStateOf(0f)

    var lastPressure by mutableStateOf(0f)

    var fabNum by mutableStateOf("")

    var hidrostatMeasurementDate by mutableStateOf("")

    var device by mutableStateOf("")
}