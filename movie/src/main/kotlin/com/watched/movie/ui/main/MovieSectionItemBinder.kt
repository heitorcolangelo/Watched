package com.watched.movie.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import com.watched.movie.databinding.ItemMovieBinding
import com.watched.movie.databinding.ItemTitleAndListBinding
import com.watched.movie.model.MovieItemUiModel
import com.watched.movie.model.MovieSectionItemUiModel
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.list.HorizontalItemDecoration

class MovieSectionItemBinder :
    BaseAdapter.Binder<ItemTitleAndListBinding, MovieSectionItemUiModel> {

    private val adapter = BaseAdapter(ItemMovieBinding::inflate, MovieItemBinder())

    override fun bindListItem(binding: ItemTitleAndListBinding, model: MovieSectionItemUiModel) {
        with(binding) {
            tvTitle.setText(model.title)

            val context = root.context
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rvList.layoutManager = layoutManager
            rvList.addItemDecoration(HorizontalItemDecoration(context))
            rvList.adapter = adapter

            adapter.submitList(model.items)
        }
    }

    class MovieItemBinder : BaseAdapter.Binder<ItemMovieBinding, MovieItemUiModel> {
        override fun bindListItem(binding: ItemMovieBinding, model: MovieItemUiModel) {
            binding.ivMoviePoster.setImageURI(model.posterPath)
        }
    }
}
