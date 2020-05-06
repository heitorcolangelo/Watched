package com.heitorcolangelo.data.local.movie.mapper

import com.heitorcolangelo.data.local.common.mapper.EntityDataMapper
import com.heitorcolangelo.data.local.movie.entity.MovieEntity
import com.heitorcolangelo.data.movie.model.MovieDataModel
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
