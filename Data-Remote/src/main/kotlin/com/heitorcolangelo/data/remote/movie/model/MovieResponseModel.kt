package com.heitorcolangelo.data.remote.movie.model

import com.heitorcolangelo.data.remote.common.model.ResponseModel
import com.squareup.moshi.Json
import java.util.Date

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
    val releaseDate: Date?
) : ResponseModel
