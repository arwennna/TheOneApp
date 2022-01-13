package com.example.lotr.ui.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lotr.databinding.QuoteItemBinding
import com.example.lotr.logic.entity.Quote

class QuoteListAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Quote, QuoteListAdapter.QuoteListViewHolder>(DiffCallback) {

    class QuoteListViewHolder(private var binding: QuoteItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            binding.property = quote
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Quote>() {
        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem._id == newItem._id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): QuoteListViewHolder {
        return QuoteListViewHolder(QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) {
        val quote = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(quote)
        }
        holder.bind(quote)
    }

    class OnClickListener(val clickListener: (quote: Quote) -> Unit) {
        fun onClick(quote: Quote) = clickListener(quote)
    }
}