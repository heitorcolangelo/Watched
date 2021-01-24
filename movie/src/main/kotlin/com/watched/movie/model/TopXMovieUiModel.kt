package com.watched.movie.model

import com.watched.presentation.common.model.ItemUiModel
import com.watched.presentation.common.model.MovieImageUiModel

data class TopXMovieUiModel(
    override val id: String,
    val posterPath: String,
    val position: Int
) : ItemUiModel {
    companion object {
        val posterSize = MovieImageUiModel.Size.MEDIUM
        const val START_POSITION = 1
    }
}
