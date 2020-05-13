package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.presentation.common.mapper.MonthAndYearDateDomainUiMapper
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.common.model.MediaImageUiModel
import javax.inject.Inject

class MovieDetailsDomainUiMapper @Inject constructor(
    private val dateMapper: MonthAndYearDateDomainUiMapper,
    private val buildConfig: BuildConfiguration
) : DomainUiMapper<MovieDomainModel, MovieDetailsUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): MovieDetailsUiModel {
        return with(domainModel) {
            MovieDetailsUiModel(
                movieId = id,
                title = title,
                overview = overview,
                releaseDate = dateMapper.mapToUiModel(releaseDate),
                voteAverage = voteAverage,
                backdropPath = MediaImageUiModel.Medium(buildConfig.imageBaseUrl(), backdropPath)
            )
        }
    }
}
