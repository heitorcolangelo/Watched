package com.watched.movie.ui.main

import com.watched.movie.R
import com.watched.movie.databinding.ItemMovieTopXBinding
import com.watched.movie.model.TopXMovieUiModel
import com.watched.presentation.common.list.BaseAdapter

class MovieTopXItemBinder : BaseAdapter.Binder<ItemMovieTopXBinding, TopXMovieUiModel> {
    override fun bindListItem(binding: ItemMovieTopXBinding, model: TopXMovieUiModel) {
        with(binding) {
            ivPoster.setImageURI(model.posterPath)
            tvTopX.text = root.context.getString(R.string.top_x_today, model.position)
        }
    }
}
