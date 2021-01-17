package com.watched.data.local.movie.mapper

import com.watched.data.local.common.mapper.EntityDataMapper
import com.watched.data.local.movie.entity.MovieEntity
import com.watched.data.movie.model.MovieDataModel
import javax.inject.Inject

class MovieEntityDataMapper @Inject constructor() : EntityDataMapper<MovieEntity, MovieDataModel> {
    override fun mapToDataModel(entity: MovieEntity): MovieDataModel {
        return with(entity) {
            MovieDataModel(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }

    override fun mapToEntity(dataModel: MovieDataModel): MovieEntity {
        return with(dataModel) {
            MovieEntity(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }
}
