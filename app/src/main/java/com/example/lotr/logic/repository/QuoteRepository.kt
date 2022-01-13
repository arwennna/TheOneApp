package com.example.lotr.logic.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.logic.entity.Quote
import com.example.lotr.logic.network.LOTRApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(private val database: AppDatabase) {

    private val token = "Bearer yX_dU4qFrxA3UQeIT-ic"

    val quoteList: LiveData<List<Quote>> = database.quoteDAO.getAll()

    suspend fun refreshQuotes() {
        withContext(Dispatchers.IO) {
            Log.d("IMPORTANT!", "Refreshed")
            val quotes = LOTRApi.retrofitService.getQuotes(token).docs
            database.quoteDAO.insertAll(quotes as List<Quote>)
        }
    }
}