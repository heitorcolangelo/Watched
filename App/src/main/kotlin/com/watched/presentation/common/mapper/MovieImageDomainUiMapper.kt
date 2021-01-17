package com.watched.presentation.common.mapper

import com.watched.data.remote.common.BuildConfiguration
import com.watched.domain.movie.model.MovieImageDomainModel
import com.watched.presentation.common.model.MovieImageUiModel
import javax.inject.Inject

class MovieImageDomainUiMapper @Inject constructor(
    private val buildConfig: BuildConfiguration
) : DomainUiMapper<MovieImageDomainModel, MovieImageUiModel> {
    override fun mapToUiModel(domainModel: MovieImageDomainModel): MovieImageUiModel {
        return MovieImageUiModel(buildConfig.imageBaseUrl(), domainModel.path)
    }
}
