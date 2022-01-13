package com.example.lotr.ui.movie.movie_list

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.entity.Movie
import com.example.lotr.logic.entity.MovieWatchlist
import com.example.lotr.logic.network.LOTRApi
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    enum class MovieApiStatus { LOADING, ERROR, DONE }

    private val token = "Bearer yX_dU4qFrxA3UQeIT-ic"

    private val _properties = MutableLiveData<List<Movie>>()

    val properties: LiveData<List<Movie>>
        get() = _properties


    private val _status = MutableLiveData<MovieApiStatus>()

    val status: LiveData<MovieApiStatus>
        get() = _status


    init {
        getMovieProperties()
    }

    private val _navigateToSelectedProperty = MutableLiveData<Movie>()
    val navigateToSelectedProperty: LiveData<Movie>
        get() = _navigateToSelectedProperty

    private fun getMovieProperties() {
        viewModelScope.launch {
            try {
                _properties.value = LOTRApi.retrofitService.getMovies(token).docs
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                Log.d("AAAA", e.toString());
                _status.value = MovieApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }


    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedProperty.value = movie
    }


    @SuppressLint("NullSafeMutableLiveData")
    fun displayMovieDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}