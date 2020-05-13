package com.heitorcolangelo.movie.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.heitorcolangelo.movie.databinding.ListItemMovieBinding
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.view.BaseAdapter
import com.heitorcolangelo.presentation.common.view.BaseViewHolder

class MovieListAdapter(
    private val items: List<MovieItemUiModel>
) : BaseAdapter<MovieItemUiModel, MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val uiModel = items[position]
        holder.bind(uiModel)
        holder.containerView.setOnClickListener {
            itemClickListener?.invoke(uiModel)
        }
    }

    override fun onItemClicked(listener: (model: MovieItemUiModel) -> Unit) {
        itemClickListener = listener
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
