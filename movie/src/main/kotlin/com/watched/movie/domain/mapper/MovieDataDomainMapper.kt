package com.watched.movie.domain.mapper

import com.watched.data.common.mapper.DataDomainMapper
import com.watched.domain.common.model.RawDateDomainModel
import com.watched.domain.media.MediaImageDomainModel
import com.watched.movie.data.model.MovieDataModel
import com.watched.movie.domain.model.MovieDomainModel
import javax.inject.Inject

class MovieDataDomainMapper @Inject constructor() :
    DataDomainMapper<MovieDataModel, MovieDomainModel> {
    override fun mapToDomainModel(dataModel: MovieDataModel): MovieDomainModel {
        return with(dataModel) {
            val backdrop = MediaImageDomainModel(backdropPath)
            val poster = MediaImageDomainModel(posterPath)
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
