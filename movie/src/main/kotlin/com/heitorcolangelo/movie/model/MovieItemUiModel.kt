package com.heitorcolangelo.movie.model

import com.heitorcolangelo.presentation.common.model.ItemUiModel
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel

data class MovieItemUiModel(
    override val id: String,
    val title: String,
    val poster: MovieImageUiModel
) : ItemUiModel(id) {
    override fun areContentsTheSame(other: ItemUiModel): Boolean {
        if (other !is MovieItemUiModel) {
            return false
        }

        return this == other
    }
}
