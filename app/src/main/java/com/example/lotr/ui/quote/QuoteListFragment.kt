package com.example.lotr.ui.quote

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lotr.R
import com.example.lotr.databinding.QuoteListFragmentBinding

class QuoteListFragment : Fragment() {

    private val viewModel: QuoteListViewModel by lazy {
        ViewModelProvider(this).get(QuoteListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = QuoteListFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.quoteList.adapter = QuoteListAdapter(QuoteListAdapter.OnClickListener {
            it
        })

        val recycler_view = binding.quoteList
        val decorator = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.divider_dark)!!)
        recycler_view.addItemDecoration(decorator)

        return binding.root
    }
}