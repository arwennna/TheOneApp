package com.example.lotr.ui.watchlist.edit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.entity.Movie
import com.example.lotr.logic.entity.MovieWatchlist
import com.example.lotr.ui.movie.movie_detail.MovieDetailViewModel

class EditWatchlistViewModelFactory(
    private val movie: MovieWatchlist,
    private  val dataSource: MovieWatchlistDAO,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditWatchlistViewModel::class.java)) {
            return EditWatchlistViewModel(movie, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}