package com.watched.data.common.model

sealed class SortOptionsDataModel : DataModel {
    object Popularity : SortOptionsDataModel()
    object TopRated : SortOptionsDataModel()
}
