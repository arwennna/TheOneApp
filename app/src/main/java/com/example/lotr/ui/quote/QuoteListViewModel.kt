package com.example.lotr.ui.quote

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.logic.repository.QuoteRepository
import kotlinx.coroutines.launch
import java.io.IOException

class QuoteListViewModel(application: Application) : AndroidViewModel(application) {

    private val token = "Bearer yX_dU4qFrxA3UQeIT-ic"

    private val quoteRepository = QuoteRepository(AppDatabase.getInstance(application))

    val quotes = quoteRepository.quoteList

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                quoteRepository.refreshQuotes()
                Log.d("Success", "Ima mreze")
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                Log.d("Fail", "Nema mreze")
                if(quotes.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}