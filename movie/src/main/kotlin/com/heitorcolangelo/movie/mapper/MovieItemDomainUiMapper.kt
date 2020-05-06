package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.common.model.PosterUiModel
import javax.inject.Inject

class MovieItemDomainUiMapper @Inject constructor(
    private val buildConfiguration: BuildConfiguration
) : DomainUiMapper<MovieDomainModel, MovieItemUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): MovieItemUiModel {
        return MovieItemUiModel(
            id = domainModel.id,
            title = domainModel.title,
            poster = PosterUiModel.SmallPosterUiModel(
                buildConfiguration.imageBaseUrl(),
                domainModel.posterPath
            )
        )
    }
}
