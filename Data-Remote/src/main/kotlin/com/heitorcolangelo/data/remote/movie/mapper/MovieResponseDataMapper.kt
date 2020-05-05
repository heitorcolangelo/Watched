package com.heitorcolangelo.data.remote.movie.mapper

import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.remote.common.mapper.response.PageResponseDataMapperImpl
import com.heitorcolangelo.data.remote.common.mapper.response.ResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import java.util.Date
import javax.inject.Inject

class MovieResponseDataMapper @Inject constructor(
) : ResponseDataMapper<MovieResponseModel, MovieDataModel> {
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
    itemMapper: MovieResponseDataMapper
) : PageResponseDataMapperImpl<MovieResponseModel, MovieDataModel>(itemMapper)
