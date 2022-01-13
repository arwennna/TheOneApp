package com.example.lotr.logic.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey @ColumnInfo(name = "_id") var _id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "runtimeInMinutes") var runtimeInMinutes: Int?,
    @ColumnInfo(name = "budgetInMillions") var budgetInMillions: Int?,
    @ColumnInfo(name = "academyAwardNominations") var academyAwardNominations: Int?,
    @ColumnInfo(name = "academyAwardWins") var academyAwardWins: Int?,
    @ColumnInfo(name = "rottenTomatoesScore") var rottenTomatoesScore: Double?
): Parcelable {

}

class MovieJSON {
    var docs: List<Movie>? = null
    var total: Int? = null
    var limit: Int? = null
    var offset: Int? = null
    var page: Int? = null
    var pages: Int? = null
}