package com.example.lotr.logic.DAO

import androidx.room.*
import com.example.lotr.logic.entity.Movie


@Dao
interface MovieDAO{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie?)

    @Update
    fun updateMovie(movie: Movie?)

    @Delete
    fun deleteMovie(movie: Movie?)

    @Query("select * from movies where _id = :id")
    fun loadMovie(id: String): Movie?

    @Query("delete from movies")
    suspend fun deleteAll()

    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movies order by academyAwardWins, academyAwardNominations desc")
    fun getAllSortedByAwards(): List<Movie>

    @Query("SELECT * FROM movies order by rottenTomatoesScore desc")
    fun getAllSortedByRottenTomatoesScore(): List<Movie>

    @Query("SELECT * FROM movies where name = :movieName")
    fun getAllByMovie(movieName: String): List<Movie>

}