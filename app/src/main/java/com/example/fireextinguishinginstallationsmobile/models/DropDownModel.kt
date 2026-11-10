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

    var innerOptions by mutableStateOf<String?>("Адресируем")
    override fun equals(other: Any?): Boolean = this === other
    override fun hashCode(): Int = System.identityHashCode(this)
}
