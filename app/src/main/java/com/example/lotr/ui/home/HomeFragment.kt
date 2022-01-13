package com.example.lotr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lotr.R
import com.example.lotr.databinding.FragmentHomeBinding
import com.example.lotr.databinding.FragmentMenuBinding
import com.example.lotr.databinding.MovieDetailFragmentBinding
import com.example.lotr.logic.database.AppDatabase
import com.example.lotr.ui.movie.movie_detail.MovieDetailFragmentArgs
import com.example.lotr.ui.movie.movie_detail.MovieDetailViewModel
import com.example.lotr.ui.movie.movie_detail.MovieDetailViewModelFactory

class HomeFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding = FragmentHomeBinding.bind(view)

        binding.btnToMenuFragment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_to_menu_fragment -> navController!!.navigate(R.id.action_homeFragment_to_menuFragment)
        }
    }

}