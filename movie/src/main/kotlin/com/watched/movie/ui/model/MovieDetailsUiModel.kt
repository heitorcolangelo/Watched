package com.watched.movie.ui.model

import com.watched.presentation.common.model.FormattedDateUiModel
import com.watched.presentation.common.model.UiModel
import com.watched.presentation.media.model.MediaImageUiModel

data class MovieDetailsUiModel(
    override val id: String,
    val title: String,
    val overview: String,
    val releaseDate: FormattedDateUiModel,
    val voteAverage: Float,
    val backdrop: MediaImageUiModel
) : UiModel {
    val backdropPath: String get() = backdrop.path(MediaImageUiModel.Size.MEDIUM)
}
