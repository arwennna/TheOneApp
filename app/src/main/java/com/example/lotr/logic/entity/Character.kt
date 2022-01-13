package com.example.lotr.logic.entity

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

class CharacterJSON {
    var docs: List<Character?>? = null
    var total: Int? = null
    var limit: Int? = null
    var offset: Int? = null
    var page: Int? = null
    var pages: Int? = null
}

@Parcelize
data class Character (
    var _id: String,
    var birth: String? = null,
    var death: String? = null,
    var hair: String? = null,
    var gender: String? = null,
    var height: String? = null,
    var realm: String? = null,
    var spouse: String? = null,
    var name: String? = null,
    var race: String? = null,
    var wikiUrl: String? = null) : Parcelable {
}