package com.watched.movie.model

import androidx.annotation.StringRes
import com.watched.presentation.common.model.ItemUiModel
import com.watched.presentation.common.model.ListUiModel

data class MovieSectionItemUiModel(
    override val id: String,
    @StringRes val title: Int,
    val list: ListUiModel<MovieItemUiModel>
) : ItemUiModel
