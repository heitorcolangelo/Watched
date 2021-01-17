package com.watched.data.movie.mapper

import com.watched.data.common.mapper.DataDomainMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.domain.common.model.RawDateDomainModel
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.MovieImageDomainModel
import javax.inject.Inject

class MovieDataDomainMapper @Inject constructor() :
    DataDomainMapper<MovieDataModel, MovieDomainModel> {
    override fun mapToDomainModel(dataModel: MovieDataModel): MovieDomainModel {
        return with(dataModel) {
            val backdrop = MovieImageDomainModel(backdropPath)
            val poster = MovieImageDomainModel(posterPath)
            val releaseDate = RawDateDomainModel(releaseDate)
            MovieDomainModel(
                id, title, overview, backdrop, poster, voteAverage, popularity, releaseDate
            )
        }
    }

    override fun mapToDataModel(domainModel: MovieDomainModel): MovieDataModel {
        return with(domainModel) {
            MovieDataModel(
                id,
                title,
                overview,
                backdrop.path,
                poster.path,
                voteAverage,
                popularity,
                releaseDate.rawDate
            )
        }
    }
}
