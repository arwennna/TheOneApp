package com.example.lotr.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lotr.databinding.CharacterItemBinding
import com.example.lotr.logic.entity.Character


class CharacterListAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Character, CharacterListAdapter.CharacterViewHolder>(DiffCallback) {

    class CharacterViewHolder(private var binding: CharacterItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.property = character
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem._id == newItem._id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
    }

    class OnClickListener(val clickListener: (character: Character) -> Unit) {
        fun onClick(character: Character) = clickListener(character)
    }
}