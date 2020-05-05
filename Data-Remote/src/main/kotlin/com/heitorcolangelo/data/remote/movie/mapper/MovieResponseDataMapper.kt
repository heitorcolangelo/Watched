package com.heitorcolangelo.data.remote.movie.mapper

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.remote.common.mapper.response.PageResponseDataMapper
import com.heitorcolangelo.data.remote.common.mapper.response.ResponseDataMapper
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import java.util.Date
import javax.inject.Inject

class MovieResponseDataMapper @Inject constructor() : ResponseDataMapper<MovieResponseModel, MovieDataModel> {
    override fun mapToDataModel(response: MovieResponseModel): MovieDataModel {
        return with(response) {
            MovieDataModel(
                movieId = id.toString(),
                title = title.orEmpty(),
                overview = overview.orEmpty(),
                backdropPath = backdropPath.orEmpty(),
                posterPath = posterPath.orEmpty(),
                voteAverage = voteAverage ?: 0.0F,
                popularity = popularity ?: 0.0F,
                releaseDate = releaseDate ?: Date()
            )
        }
    }
}

class MoviePageResponseDataMapper @Inject constructor(
    private val itemMapper: MovieResponseDataMapper
) : PageResponseDataMapper<MovieResponseModel, MovieDataModel> {
    override fun mapToPageDataModel(pageResponse: PageResponseModel<MovieResponseModel>): PageDataModel<MovieDataModel> {
        return with(pageResponse) {
            PageDataModel(
                items = results.map(itemMapper::mapToDataModel),
                page = page,
                totalItems = totalResults,
                totalPages = totalPages
            )
        }
    }
}
