package com.watched.movie.data.source.local

import com.watched.data.local.common.mapper.EntityDataMapper
import com.watched.data.local.movie.entity.MovieEntity
import com.watched.movie.data.model.MovieDataModel
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
