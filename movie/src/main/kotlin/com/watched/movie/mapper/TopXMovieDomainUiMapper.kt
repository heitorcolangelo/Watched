package com.watched.movie.mapper

import com.watched.domain.movie.model.TopXMovieDomainModel
import com.watched.movie.model.TopXMovieUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.MovieImageDomainUiMapper
import javax.inject.Inject

class TopXMovieDomainUiMapper @Inject constructor(
    private val imageMapper: MovieImageDomainUiMapper
) : DomainUiMapper<TopXMovieDomainModel, TopXMovieUiModel> {
    override fun mapToUiModel(domainModel: TopXMovieDomainModel): TopXMovieUiModel {
        return with(domainModel) {
            TopXMovieUiModel(
                id = movie.id,
                posterPath = imageMapper
                    .mapToUiModel(movie.poster)
                    .getUrl(TopXMovieUiModel.posterSize),
                position = moviePosition + TopXMovieUiModel.START_POSITION
            )
        }
    }
}
