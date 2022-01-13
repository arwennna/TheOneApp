package com.example.lotr.ui.about

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.lotr.R
import com.example.lotr.databinding.FragmentAboutTheAppBinding
import com.example.lotr.databinding.FragmentHomeBinding

class AboutTheAppFragment : Fragment(), View.OnClickListener {

    var navController: NavController? = null
    private lateinit var binding: FragmentAboutTheAppBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_about_the_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        binding = FragmentAboutTheAppBinding.bind(view)

        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!! .id) {
            R.id.btn_share -> share(v)
        }
    }

    private fun share(view: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "If you'd like to learn more about J.R.R. Tolkien's work, you should definitely " +
                    "check out The One App!"
        )
        intent.type = "text/plain"
        view.context.startActivity(intent)
    }

}