package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.LatestMovieUiModel
import com.heitorcolangelo.presentation.common.mapper.DomainUiMapper
import com.heitorcolangelo.presentation.common.mapper.MovieImageDomainUiMapper
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