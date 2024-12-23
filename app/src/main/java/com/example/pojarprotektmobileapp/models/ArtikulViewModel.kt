package com.example.pojarprotektmobileapp.models

import com.example.pojarprotektmobileapp.models.MyViewModel

class ArtikulViewModel : MyViewModel() {
    override fun getTableName(): String {
        return "ArtikulsDB"
    }

    override fun isGrey(): Boolean {
        return false
    }

    override fun getType(): Int {
        return 0
    }
}