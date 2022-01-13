package com.example.lotr.ui.watchlist.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lotr.R
import com.example.lotr.databinding.WatchlistFragmentBinding

class WatchlistFragment : Fragment() {

    private val viewModel: WatchlistViewModel by lazy {
        ViewModelProvider(this).get(WatchlistViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = WatchlistFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.watchlistViewModel = viewModel

        binding.movieWatchlist.adapter = WatchlistAdapter(WatchlistAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(
                    WatchlistFragmentDirections.actionWatchlistFragmentToEditWatchlist(
                        it
                    )
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        val recycler_view = binding.movieWatchlist
        val decorator = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.divider)!!)
        recycler_view.addItemDecoration(decorator)

        return binding.root
    }

}