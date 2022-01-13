package com.example.lotr.logic.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lotr.ui.character.CharacterListAdapter
import com.example.lotr.ui.movie.movie_list.MovieListAdapter
import com.example.lotr.ui.watchlist.list.WatchlistAdapter
import com.example.lotr.logic.entity.Character
import com.example.lotr.logic.entity.Quote
import com.example.lotr.ui.quote.QuoteListAdapter

@BindingAdapter("listData")
fun bindMovieRecyclerView(recyclerView: RecyclerView,
                          data: List<com.example.lotr.logic.entity.Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindCharacterListRecyclerView(recyclerView: RecyclerView,
                     data: List<Character>?) {
    val adapter = recyclerView.adapter as CharacterListAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindMovieWatchlistRecyclerView(recyclerView: RecyclerView,
                          data: List<com.example.lotr.logic.entity.MovieWatchlist>?) {
    val adapter = recyclerView.adapter as WatchlistAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindQuoteListRecyclerView(recyclerView: RecyclerView,
                                   data: List<Quote>?) {
    val adapter = recyclerView.adapter as QuoteListAdapter
    adapter.submitList(data)
}