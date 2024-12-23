package com.example.pojarprotektmobileapp.models

import androidx.annotation.Keep


@Keep
 data class ArtikulModel (
    val artikul: String, val quantity: Int, val med: String, val price: Float,
    val date: String, val invoice: String, val kontragent: String, val barcode: String, val percentProfit: String,
    val person: String)  {

}


fun getArtikulModels() : MutableList<ArtikulModel> {
    return mutableListOf(
        ArtikulModel("Cake",0,"",0.0f,"","","","","",""),
        ArtikulModel("Cheese",0,"",0.0f,"","","","","",""),
        ArtikulModel("Eggs",0,"",0.0f,"","","","","","")
    )

}








