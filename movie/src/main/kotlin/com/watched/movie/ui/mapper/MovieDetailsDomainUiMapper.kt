package com.watched.movie.ui.mapper

import com.watched.movie.domain.model.MovieDomainModel
import com.watched.movie.ui.model.MovieDetailsUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.MonthAndYearDateDomainUiMapper
import com.watched.presentation.media.mapper.MediaImageDomainUiMapper
import javax.inject.Inject

class MovieDetailsDomainUiMapper @Inject constructor(
    private val dateMapper: MonthAndYearDateDomainUiMapper,
    private val imageMapper: MediaImageDomainUiMapper
) : DomainUiMapper<MovieDomainModel, MovieDetailsUiModel> {

    override fun mapToUiModel(domainModel: MovieDomainModel): MovieDetailsUiModel {
        return with(domainModel) {
            MovieDetailsUiModel(
                id = id,
                title = title,
                overview = overview,
                releaseDate = dateMapper.mapToUiModel(releaseDate),
                voteAverage = voteAverage,
                backdrop = imageMapper.mapToUiModel(backdrop)
            )
        }
    }
}
