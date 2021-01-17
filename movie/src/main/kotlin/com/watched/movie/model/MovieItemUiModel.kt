package com.watched.movie.model

import com.watched.presentation.common.model.ItemUiModel

data class MovieItemUiModel(
    override val id: String,
    val posterPath: String
) : ItemUiModel
