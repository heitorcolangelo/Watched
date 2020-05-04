package com.heitorcolangelo.data.movie.mapper

import com.heitorcolangelo.data.common.model.DataDomainMapper
import com.heitorcolangelo.data.common.model.DataListDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import javax.inject.Inject

class MovieDataDomainMapper @Inject constructor(
) : DataDomainMapper<MovieDataModel, MovieDomainModel> {
    override fun mapToDomainModel(dataModel: MovieDataModel): MovieDomainModel {
        return with(dataModel) {
            MovieDomainModel(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }

    override fun mapToDataModel(domainModel: MovieDomainModel): MovieDataModel {
        return with(domainModel) {
            MovieDataModel(
                id, title, overview, backdropPath, posterPath, voteAverage, popularity, releaseDate
            )
        }
    }
}

class PagedMovieDataDomainMapper @Inject constructor(
    private val itemMapper: MovieDataDomainMapper
): DataListDomainMapper<MovieDataModel, PageDomainModel<MovieDomainModel>> {
    override fun mapToDomainModel(dataModelList: List<MovieDataModel>): PageDomainModel<MovieDomainModel> {
        val domainModelList = dataModelList.map(itemMapper::mapToDomainModel)
        return PageDomainModel(domainModelList)
    }

    override fun mapToDataModelList(domainModel: PageDomainModel<MovieDomainModel>): List<MovieDataModel> {
        return domainModel.items.map(itemMapper::mapToDataModel)
    }
}