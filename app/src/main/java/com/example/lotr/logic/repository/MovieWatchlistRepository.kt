package com.example.lotr.logic.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.logic.entity.MovieWatchlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class MovieWatchlistRepository(private val database: AppDatabase) {
    val watchlist: LiveData<List<MovieWatchlist>> = database.movieWatchlistDAO.getAll()
}