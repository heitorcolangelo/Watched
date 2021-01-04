package com.heitorcolangelo.movie.model

import com.heitorcolangelo.presentation.common.model.ItemUiModel
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel

data class LatestMovieUiModel(
    override val id: String,
    val posterPath: String
) : ItemUiModel {
    companion object {
        val posterSize = MovieImageUiModel.Size.MEDIUM
    }
}
