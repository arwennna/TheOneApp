package com.example.lotr.logic.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lotr.logic.DAO.MovieDAO
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.DAO.QuoteDAO
import com.example.lotr.logic.entity.Movie
import com.example.lotr.logic.entity.MovieWatchlist
import com.example.lotr.logic.entity.Quote

@Database(entities = [Movie::class, MovieWatchlist::class, Quote::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDAO: MovieDAO
    abstract val movieWatchlistDAO: MovieWatchlistDAO
    abstract val quoteDAO: QuoteDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}