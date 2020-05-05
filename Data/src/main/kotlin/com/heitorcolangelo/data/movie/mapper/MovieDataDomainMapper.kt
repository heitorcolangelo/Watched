package com.heitorcolangelo.data.movie.mapper

import com.heitorcolangelo.data.common.mapper.DataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import javax.inject.Inject

class MovieDataDomainMapper @Inject constructor() : DataDomainMapper<MovieDataModel, MovieDomainModel> {
    override fun mapToDomainModel(dataModel: MovieDataModel): MovieDomainModel {
        return with(dataModel) {
            MovieDomainModel(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }

    override fun mapToDataModel(domainModel: MovieDomainModel): MovieDataModel {
        return with(domainModel) {
            MovieDataModel(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }
}
