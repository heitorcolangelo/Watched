package com.watched.movie.mapper

import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.MovieListDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.movie.R
import com.watched.movie.model.MovieItemUiModel
import com.watched.movie.model.MovieSectionItemUiModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.ListDomainUiMapper
import javax.inject.Inject

class MovieSectionDomainUiMapper @Inject constructor(
    private val movieListMapper: ListDomainUiMapper<MovieDomainModel, MovieItemUiModel>
) : DomainUiMapper<MovieListDomainModel, MovieSectionItemUiModel> {

    override fun mapToUiModel(domainModel: MovieListDomainModel): MovieSectionItemUiModel {
        return MovieSectionItemUiModel(
            id = domainModel.id,
            title = getSectionTitle(domainModel.sortOptions),
            list = movieListMapper.mapToUiModel(domainModel)
        )
    }

    private fun getSectionTitle(sortOptionsDomainModel: SortOptionsDomainModel?): Int {
        return when (sortOptionsDomainModel) {
            SortOptionsDomainModel.Popularity -> R.string.popular_movies
            SortOptionsDomainModel.TopRated -> R.string.top_rated_movies
            else -> R.string.no_sort_option_movies
        }
    }
}
