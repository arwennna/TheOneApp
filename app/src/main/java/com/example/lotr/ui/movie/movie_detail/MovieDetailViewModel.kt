package com.example.lotr.ui.movie.movie_detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.entity.Movie
import com.example.lotr.logic.entity.MovieWatchlist
import kotlinx.coroutines.launch

class MovieDetailViewModel(movie: Movie,
                           val database: MovieWatchlistDAO,
                           app: Application
) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Movie>()

    val selectedProperty: LiveData<Movie>
        get() = _selectedProperty

    private var current_movie: Movie? = null

    init {
        _selectedProperty.value = movie
        current_movie = movie
    }

    private suspend fun insert(watchlistMovie: Movie) {
        var myMovie: MovieWatchlist = MovieWatchlist(watchlistMovie._id,
            watchlistMovie.name, null, null)
        database.insertMovie(myMovie)
    }

    fun onAddToWatchlist() {
        viewModelScope.launch {
            current_movie?.let { insert(it) }
        }
    }


    val displayName = Transformations.map(selectedProperty) {
        it.name
    }

    val displayRuntime = Transformations.map(selectedProperty) {
        it.runtimeInMinutes.toString()
    }

    val displayBudget = Transformations.map(selectedProperty) {
        it.budgetInMillions.toString()
    }

    val displayRottenTomatoes = Transformations.map(selectedProperty) {
        it.rottenTomatoesScore?.toInt().toString()
    }

    val displayAwardNominations = Transformations.map(selectedProperty) {
        it.academyAwardNominations.toString()
    }
    val displayAwardWins = Transformations.map(selectedProperty) {
        it.academyAwardWins.toString()
    }

}