package com.watched.movie.mapper

import com.watched.domain.movie.model.MovieDomainModel
import com.watched.movie.model.MovieItemUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.MovieImageDomainUiMapper
import com.watched.presentation.common.model.MovieImageUiModel
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
