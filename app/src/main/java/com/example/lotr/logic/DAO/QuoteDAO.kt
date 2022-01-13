package com.example.lotr.logic.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lotr.logic.entity.Quote

@Dao
interface QuoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(quotes: List<Quote>)

    @Update
    fun updateQuote(quote: Quote?)

    @Delete
    fun deleteQuote(quote: Quote?)

    @Query("select * from quotes where _id = :id")
    fun loadQuote(id: String): Quote?

    @Query("delete from quotes")
    suspend fun deleteAll()

    @Query("SELECT * FROM quotes")
    fun getAll(): LiveData<List<Quote>>

    @Query("SELECT * FROM quotes where movie = :movie")
    fun getAllByMovie(movie: String): LiveData<List<Quote>>

    @Query("SELECT * FROM quotes where character = :character")
    fun getAllByCharacter(character : String): LiveData<List<Quote>>

}