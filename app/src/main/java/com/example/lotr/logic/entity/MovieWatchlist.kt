package com.example.lotr.logic.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "moviewatchlist")
data class MovieWatchlist(
    @PrimaryKey @ColumnInfo(name = "_id") var _id: String,
    @ColumnInfo(name = "movieName") var movieName: String,
    @ColumnInfo(name = "rating") var rating: Int?,
    @ColumnInfo(name = "comment") var comment: String?
): Parcelable {

}