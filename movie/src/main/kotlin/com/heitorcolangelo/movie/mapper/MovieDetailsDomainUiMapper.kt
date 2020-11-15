package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.presentation.common.mapper.DomainUiMapper
import com.heitorcolangelo.presentation.common.mapper.MonthAndYearDateDomainUiMapper
import com.heitorcolangelo.presentation.common.mapper.MovieImageDomainUiMapper
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel
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
