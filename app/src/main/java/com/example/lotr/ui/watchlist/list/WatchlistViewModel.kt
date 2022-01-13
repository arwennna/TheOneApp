package com.example.lotr.ui.watchlist.list

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.logic.entity.MovieWatchlist
import com.example.lotr.logic.repository.MovieWatchlistRepository
import kotlinx.coroutines.launch
import java.io.IOException

class WatchlistViewModel(application: Application) : AndroidViewModel(application) {

    private val movieWatchlistRepository = MovieWatchlistRepository(AppDatabase.getInstance(application))

    val movies = movieWatchlistRepository.watchlist

    init {
    }

    private val _navigateToSelectedProperty = MutableLiveData<MovieWatchlist>()
    val navigateToSelectedProperty: LiveData<MovieWatchlist>
        get() = _navigateToSelectedProperty


    fun displayPropertyDetails(movieWatchlist: MovieWatchlist) {
        _navigateToSelectedProperty.value = movieWatchlist
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}