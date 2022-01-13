package com.example.lotr.ui.watchlist.edit

import android.annotation.SuppressLint
import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.*
import com.example.lotr.logic.DAO.MovieWatchlistDAO
import com.example.lotr.logic.entity.Movie
import com.example.lotr.logic.entity.MovieWatchlist
import kotlinx.coroutines.launch

class EditWatchlistViewModel(movieWatchlist: MovieWatchlist,
                             val database: MovieWatchlistDAO,
                             app: Application
) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MovieWatchlist>()
    private var comment: String? = null
    private var rating: Int? = null

    val selectedProperty: LiveData<MovieWatchlist>
        get() = _selectedProperty

    private var oldMovieWatchlist: MovieWatchlist? = null

    init {
        _selectedProperty.value = movieWatchlist
        oldMovieWatchlist = movieWatchlist
    }

    private suspend fun update(watchlistMovie: MovieWatchlist) {
        var myMovie: MovieWatchlist = MovieWatchlist(watchlistMovie._id,
            watchlistMovie.movieName, rating, comment)
        database.updateMovie(myMovie)
        _selectedProperty.value = myMovie
        _navigateToList.value = true
    }

    fun onWatchlistItemEdited() {
        viewModelScope.launch {
            oldMovieWatchlist?.let { update(it) }
        }
    }

    suspend fun delete(watchlistMovie: MovieWatchlist) {
        database.deleteMovie(watchlistMovie)
        _navigateToList.value = true
    }

    fun onDeleteFromWatchlist() {
        viewModelScope.launch {
            oldMovieWatchlist?.let { delete(it) }
        }
    }

    fun onCommentChanged(s: Editable) {
        comment = s.toString()
    }

    fun onRatingChanged(s: Editable) {
        rating = s.toString().toInt()
    }

    val displayMovieName = Transformations.map(selectedProperty) {
        it.movieName
    }

    val displayComment = Transformations.map(selectedProperty) {
        it.comment
    }

    val displayRating = Transformations.map(selectedProperty) {
        it.rating.toString()
    }

    private val _navigateToList = MutableLiveData<Boolean>()
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList


    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete() {
        _navigateToList.value = null
    }

}