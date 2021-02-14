package com.watched.movie.mapper

import com.watched.domain.common.model.ListDomainModel
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.movie.R
import com.watched.movie.model.MovieItemUiModel
import com.watched.movie.model.MovieSectionItemUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.ListDomainUiMapper

interface PopularMoviesSectionDomainUiMapper : MovieSectionDomainUiMapper {
    override val titleResId: Int
        get() = R.string.popular_movies
}

interface MovieSectionDomainUiMapper :
    DomainUiMapper<ListDomainModel<MovieDomainModel>, MovieSectionItemUiModel> {
    val titleResId: Int
    val movieListMapper: ListDomainUiMapper<MovieDomainModel, MovieItemUiModel>

    override fun mapToUiModel(domainModel: ListDomainModel<MovieDomainModel>): MovieSectionItemUiModel {
        return MovieSectionItemUiModel(
            id = domainModel.id,
            title = titleResId,
            list = movieListMapper.mapToUiModel(domainModel)
        )
    }
}