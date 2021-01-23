package com.watched.data.movie.model

import com.watched.data.common.model.DataModel

sealed class SortOptionsDataModel : DataModel() {
    object Popularity : SortOptionsDataModel()
    object TopRated : SortOptionsDataModel()
}