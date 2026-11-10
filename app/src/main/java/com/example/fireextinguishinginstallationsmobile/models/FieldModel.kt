package com.example.fireextinguishinginstallationsmobile.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.fireextinguishinginstallationsmobile.interfaces.IModel

data class FieldModel(
    override val subTitle: String
) : IModel {

    var data by mutableStateOf("")
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = System.identityHashCode(this)

}