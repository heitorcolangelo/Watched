package com.watched.movie.model

import androidx.annotation.StringRes
import com.watched.presentation.common.model.ItemUiModel

data class MovieSectionItemUiModel(
    override val id: String = ItemUiModel.NO_ID,
    @StringRes val title: Int,
    val items: List<MovieItemUiModel>
) : ItemUiModel