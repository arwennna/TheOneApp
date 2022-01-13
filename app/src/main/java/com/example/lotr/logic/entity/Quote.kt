package com.example.lotr.logic.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

class QuoteJSON {
    var docs: List<Quote>? = null
    var total: Int? = null
    var limit: Int? = null
    var offset: Int? = null
    var page: Int? = null
    var pages: Int? = null
}

@Entity(tableName = "quotes")
@Parcelize
data class Quote (
    @PrimaryKey @ColumnInfo var _id: String,
    @ColumnInfo var dialog: String? = null,
    @ColumnInfo var movie: String? = null,
    @ColumnInfo var character: String? = null) : Parcelable {
}