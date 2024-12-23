package com.example.pojarprotektmobileapp.models

import com.example.pojarprotektmobileapp.models.MyViewModel

class GreyArtikulViewModel : MyViewModel() {
    override fun getTableName(): String {
        return "GreyArtikulsDB"
    }

    override fun isGrey(): Boolean {
        return true
    }

    override fun getType(): Int {
        return 2
    }
}