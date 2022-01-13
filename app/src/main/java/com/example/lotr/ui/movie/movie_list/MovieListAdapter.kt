package com.example.lotr.ui.movie.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lotr.databinding.MovieItemBinding
import com.example.lotr.logic.entity.Movie


class MovieListAdapter(private val onClickListener: OnClickListener ) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {

    class MovieViewHolder(private var binding: MovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.property = movie
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem._id == newItem._id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }
}