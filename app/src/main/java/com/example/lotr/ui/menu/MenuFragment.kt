package com.example.lotr.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lotr.R
import com.example.lotr.databinding.FragmentMenuBinding

class MenuFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding = FragmentMenuBinding.bind(view)

        binding.btnToWatchlist.setOnClickListener(this)
        binding.btnToCharacters.setOnClickListener(this)
        binding.btnToQuotes.setOnClickListener(this)
        binding.btnToMovies.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_to_quotes -> navController!!.navigate(R.id.action_menuFragment_to_quoteListFragment)
            R.id.btn_to_characters -> navController!!.navigate(R.id.action_menuFragment_to_characterListFragment)
            R.id.btn_to_watchlist -> navController!!.navigate(R.id.action_menuFragment_to_watchlistFragment)
            R.id.btn_to_movies -> navController!!.navigate(R.id.action_menuFragment_to_movieListFragment)
        }
    }

}