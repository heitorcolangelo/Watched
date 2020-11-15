package com.heitorcolangelo.movie.model

import com.heitorcolangelo.presentation.common.model.ItemUiModel

data class MovieItemUiModel(
    override val id: String,
    val posterPath: String
) : ItemUiModel