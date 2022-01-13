package com.example.lotr.ui.watchlist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lotr.databinding.WatchlistItemBinding
import com.example.lotr.logic.entity.MovieWatchlist


class WatchlistAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<MovieWatchlist, WatchlistAdapter.WatchlistViewHolder>(DiffCallback) {

    class WatchlistViewHolder(private var binding: WatchlistItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieWatchlist: MovieWatchlist) {
            binding.property = movieWatchlist
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MovieWatchlist>() {
        override fun areItemsTheSame(oldItem: MovieWatchlist, newItem: MovieWatchlist): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieWatchlist, newItem: MovieWatchlist): Boolean {
            return oldItem._id == newItem._id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WatchlistViewHolder {
        return WatchlistViewHolder(WatchlistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: WatchlistViewHolder, position: Int) {
        val movieWatchlist = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movieWatchlist)
        }
        holder.bind(movieWatchlist)
    }

    class OnClickListener(val clickListener: (movieWatchlist: MovieWatchlist) -> Unit) {
        fun onClick(movieWatchlist: MovieWatchlist) = clickListener(movieWatchlist)
    }
}