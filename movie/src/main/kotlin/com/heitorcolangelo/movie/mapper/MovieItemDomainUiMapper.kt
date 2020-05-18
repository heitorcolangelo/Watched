package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel
import javax.inject.Inject

class MovieItemDomainUiMapper @Inject constructor(
    private val buildConfig: BuildConfiguration
) : DomainUiMapper<MovieDomainModel, MovieItemUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): MovieItemUiModel {
        return with(domainModel) {
            MovieItemUiModel(
                id = id,
                title = title,
                poster = MovieImageUiModel.Small(buildConfig.imageBaseUrl(), posterPath)
            )
        }
    }
}
