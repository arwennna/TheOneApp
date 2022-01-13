package com.example.lotr.ui.watchlist.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.lotr.R
import com.example.lotr.databinding.EditWatchlistFragmentBinding
import com.example.lotr.databinding.MovieDetailFragmentBinding
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.ui.movie.movie_detail.MovieDetailFragmentArgs
import com.example.lotr.ui.movie.movie_detail.MovieDetailViewModel
import com.example.lotr.ui.movie.movie_detail.MovieDetailViewModelFactory
import com.example.lotr.ui.watchlist.list.WatchlistFragmentDirections

class EditWatchlistFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        @Suppress("UNUSED_VARIABLE")

        val binding = EditWatchlistFragmentBinding.inflate(inflater)

        val application = requireNotNull(this.activity).application

        binding.lifecycleOwner = this

        val movie = EditWatchlistFragmentArgs.fromBundle(requireArguments()).selectedProperty

        val dataSource = AppDatabase.getInstance(application).movieWatchlistDAO

        val viewModelFactory = EditWatchlistViewModelFactory(movie, dataSource, application)

        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(EditWatchlistViewModel::class.java)

        binding.editWatchlistViewModel = viewModel

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    EditWatchlistFragmentDirections.actionEditWatchlistToWatchlist()
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}