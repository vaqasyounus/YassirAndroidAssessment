package com.yassir.task.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yassir.task.R
import com.yassir.task.data.dto.movie_list.Movie
import com.yassir.task.databinding.AdapterItemBinding
import com.yassir.task.utils.Constants
import com.yassir.task.utils.loadImage

class MoviesAdapter(private val clicked: (Int) -> Unit) :
    PagingDataAdapter<Movie, MoviesAdapter.PlayersViewHolder>(PlayersDiffCallback()) {


    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        return PlayersViewHolder(AdapterItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    inner class PlayersViewHolder(
        private val binding: AdapterItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Movie?) {
            binding.let {

                it.root.setOnClickListener {
                    clicked.invoke(data?.id!!)
                }

                it.title.text = data?.title

                it.releaseDate.text = data?.releaseDate

                data?.posterPath?.let { it1 -> it.ivBanner.loadImage(it1) }

            }
        }
    }

    private class PlayersDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

}