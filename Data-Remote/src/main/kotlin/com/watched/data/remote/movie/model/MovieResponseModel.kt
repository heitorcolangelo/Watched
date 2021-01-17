package com.watched.data.remote.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.watched.data.remote.common.model.ResponseModel

@JsonClass(generateAdapter = true)
data class MovieResponseModel(
    @Json(name = "id")
    val id: Long?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Float?,
    @Json(name = "popularity")
    val popularity: Float?,
    @Json(name = "release_date")
    val releaseDate: String?
) : ResponseModel
