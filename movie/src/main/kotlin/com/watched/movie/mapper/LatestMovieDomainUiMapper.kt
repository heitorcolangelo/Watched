package com.watched.movie.mapper

import com.watched.domain.movie.model.MovieDomainModel
import com.watched.movie.model.LatestMovieUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.MovieImageDomainUiMapper
import javax.inject.Inject

class LatestMovieDomainUiMapper @Inject constructor(
    private val imageMapper: MovieImageDomainUiMapper
) : DomainUiMapper<MovieDomainModel, LatestMovieUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): LatestMovieUiModel {
        return with(domainModel) {
            LatestMovieUiModel(
                id = id,
                posterPath = imageMapper
                    .mapToUiModel(poster)
                    .getUrl(LatestMovieUiModel.posterSize)
            )
        }
    }
}
