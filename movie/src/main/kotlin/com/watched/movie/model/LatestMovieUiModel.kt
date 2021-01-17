package com.watched.movie.model

import com.watched.presentation.common.model.ItemUiModel
import com.watched.presentation.common.model.MovieImageUiModel

data class LatestMovieUiModel(
    override val id: String,
    val posterPath: String
) : ItemUiModel {
    companion object {
        val posterSize = MovieImageUiModel.Size.MEDIUM
    }
}
