package com.example.lotr.ui.movie.movie_list

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lotr.R
import com.example.lotr.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private val viewModel: com.example.lotr.ui.movie.movie_list.MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMovieListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.movieListViewModel = viewModel

        binding.movieList.adapter = MovieListAdapter(
            MovieListAdapter.OnClickListener {
                viewModel.displayMovieDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    MovieListFragmentDirections.actionNavGalleryToMovieDetailFragment(it))
                viewModel.displayMovieDetailsComplete()
            }
        })


        val recycler_view = binding.movieList
        val decorator = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.divider)!!)
        recycler_view.addItemDecoration(decorator)

        return binding.root
    }

}