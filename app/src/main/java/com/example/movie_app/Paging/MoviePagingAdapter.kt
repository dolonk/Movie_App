package com.example.movie_app.Paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.BR
import com.example.movie_app.databinding.ViewHolderMovieBinding
import com.example.movie_app.domain.Movie

class MoviePagingAdapter : PagingDataAdapter<Movie, MoviePagingAdapter.MyViewHolder>(DIFF_UTIL) {

    inner class MyViewHolder(val viewDataBinding: ViewHolderMovieBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root)

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.movie, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ViewHolderMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}