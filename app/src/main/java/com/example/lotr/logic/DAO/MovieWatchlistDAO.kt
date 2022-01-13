package com.example.lotr.logic.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lotr.logic.entity.MovieWatchlist
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieWatchlistDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieWatchlist?)

    @Update
    suspend fun updateMovie(movie: MovieWatchlist?)

    @Delete
    suspend fun deleteMovie(movie: MovieWatchlist?)

    @Query("select * from moviewatchlist where _id = :id")
    fun loadMovie(id: String): MovieWatchlist?

    @Query("delete from moviewatchlist")
    suspend fun deleteAll()

    @Query("SELECT * FROM moviewatchlist")
    fun getAll(): LiveData<List<MovieWatchlist>>

}