package com.example.lotr.ui.movie.movie_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lotr.R
import com.example.lotr.databinding.MovieDetailFragmentBinding
import com.example.lotr.logic.database.AppDatabase

class MovieDetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")

        val binding = MovieDetailFragmentBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        binding.lifecycleOwner = this

        val movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty

        val dataSource = AppDatabase.getInstance(application).movieWatchlistDAO

        val viewModelFactory = MovieDetailViewModelFactory(movie, dataSource, application)

        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(MovieDetailViewModel::class.java)

        return binding.root
    }
}