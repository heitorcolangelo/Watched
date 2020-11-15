package com.heitorcolangelo.presentation.common.mapper

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.movie.model.MovieImageDomainModel
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel
import javax.inject.Inject

class MovieImageDomainUiMapper @Inject constructor(
    private val buildConfig: BuildConfiguration
) : DomainUiMapper<MovieImageDomainModel, MovieImageUiModel> {
    override fun mapToUiModel(domainModel: MovieImageDomainModel): MovieImageUiModel {
        return MovieImageUiModel(buildConfig.imageBaseUrl(), domainModel.path)
    }
}
