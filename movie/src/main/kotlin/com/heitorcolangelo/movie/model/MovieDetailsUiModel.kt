package com.heitorcolangelo.movie.model

import com.heitorcolangelo.presentation.common.model.FormattedDateUiModel
import com.heitorcolangelo.presentation.common.model.UiModel

data class MovieDetailsUiModel(
    val movieId: String,
    val title: String,
    val overview: String,
    val releaseDate: FormattedDateUiModel,
    val voteAverage: Float,
    val backdropPath: String
) : UiModel(movieId)
