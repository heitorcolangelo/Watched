package com.heitorcolangelo.movie.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heitorcolangelo.movie.databinding.ListItemMovieBinding
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.view.BaseViewHolder

class MovieListAdapter(
    private val items: List<MovieItemUiModel>
) : RecyclerView.Adapter<MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return 20
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class MovieItemViewHolder(
    private val binding: ListItemMovieBinding
) : BaseViewHolder<MovieItemUiModel>(binding.root) {
    override fun bind(model: MovieItemUiModel) {
        binding.tvMovieTitle.text = model.title
        Glide.with(containerView)
            .load(model.poster.getFullUrl())
            .into(binding.ivMoviePoster)
    }
}