package com.example.lotr.ui.character

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lotr.R
import com.example.lotr.databinding.FragmentCharacterBinding
import com.example.lotr.logic.network.CharacterApiFilter


class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by lazy {
        ViewModelProvider(this).get(CharacterListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentCharacterBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.characterViewModel = viewModel

        binding.characterList.adapter = CharacterListAdapter(CharacterListAdapter.OnClickListener {
            it
        })

        val recycler_view = binding.characterList
        val decorator = DividerItemDecoration(this.requireContext(), LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.divider)!!)
        recycler_view.addItemDecoration(decorator)

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_characters, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_elves -> CharacterApiFilter.SHOW_ELVES
                R.id.show_hobbits -> CharacterApiFilter.SHOW_HOBBITS
                R.id.show_dwarves -> CharacterApiFilter.SHOW_DWARVES
                R.id.show_humans -> CharacterApiFilter.SHOW_HUMANS
                else -> CharacterApiFilter.SHOW_ALL
            }
        )
        return true
    }
}