package com.watched.data.remote.movie.mapper

import com.watched.data.movie.model.MovieDataModel
import com.watched.data.remote.common.mapper.response.PageResponseDataMapperImpl
import com.watched.data.remote.common.mapper.response.ResponseDataMapper
import com.watched.data.remote.movie.model.MovieResponseModel
import javax.inject.Inject

class MovieResponseDataMapper @Inject constructor() :
    ResponseDataMapper<MovieResponseModel, MovieDataModel> {
    override fun mapToDataModel(response: MovieResponseModel): MovieDataModel {
        return with(response) {
            MovieDataModel(
                id = id.toString(),
                title = title.orEmpty(),
                overview = overview.orEmpty(),
                backdropPath = backdropPath.orEmpty(),
                posterPath = posterPath.orEmpty(),
                voteAverage = voteAverage ?: 0.0F,
                popularity = popularity ?: 0.0F,
                releaseDate = releaseDate.orEmpty()
            )
        }
    }
}

class MoviePageResponseDataMapper @Inject constructor(
    itemMapper: MovieResponseDataMapper
) : PageResponseDataMapperImpl<MovieResponseModel, MovieDataModel>(itemMapper)
