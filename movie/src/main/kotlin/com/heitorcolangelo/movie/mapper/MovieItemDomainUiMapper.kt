package com.heitorcolangelo.movie.mapper

import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.presentation.common.mapper.DomainUiMapper
import com.heitorcolangelo.presentation.common.mapper.MovieImageDomainUiMapper
import com.heitorcolangelo.presentation.common.model.MovieImageUiModel
import javax.inject.Inject

class MovieItemDomainUiMapper @Inject constructor(
    private val imageMapper: MovieImageDomainUiMapper
) : DomainUiMapper<MovieDomainModel, MovieItemUiModel> {
    override fun mapToUiModel(domainModel: MovieDomainModel): MovieItemUiModel {
        return with(domainModel) {
            MovieItemUiModel(
                id = id,
                posterPath = imageMapper.mapToUiModel(poster).getUrl(MovieImageUiModel.Size.SMALL)
            )
        }
    }
}
