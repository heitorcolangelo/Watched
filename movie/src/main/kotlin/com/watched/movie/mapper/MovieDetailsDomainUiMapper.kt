package com.watched.movie.mapper

import com.watched.domain.movie.model.MovieDomainModel
import com.watched.movie.model.MovieDetailsUiModel
import com.watched.movie.model.MovieImageUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.MonthAndYearDateDomainUiMapper
import javax.inject.Inject

class MovieDetailsDomainUiMapper @Inject constructor(
    private val dateMapper: MonthAndYearDateDomainUiMapper,
    private val imageMapper: MovieImageDomainUiMapper
) : DomainUiMapper<MovieDomainModel, MovieDetailsUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): MovieDetailsUiModel {
        return with(domainModel) {
            MovieDetailsUiModel(
                id = id,
                title = title,
                overview = overview,
                releaseDate = dateMapper.mapToUiModel(releaseDate),
                voteAverage = voteAverage,
                backdropPath = imageMapper.mapToUiModel(backdrop)
                    .getUrl(MovieImageUiModel.Size.MEDIUM)
            )
        }
    }
}
