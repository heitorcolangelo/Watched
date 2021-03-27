package com.watched.movie.data.source.remote

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.model.SortOptionsDataModel
import com.watched.data.remote.common.model.PageResponseModel
import com.watched.data.remote.movie.api.MovieApiService
import com.watched.data.remote.movie.model.MovieResponseModel
import com.watched.movie.data.model.MovieDataModel
import com.watched.movie.data.source.MovieRemoteDataSource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val pageMapper: MoviePageResponseDataMapper,
    private val movieMapper: MovieResponseDataMapper,
    private val api: MovieApiService
) : MovieRemoteDataSource {

    override val firstPage: Int = PageResponseModel.FIRST_PAGE

    override suspend fun getMovies(
        page: Int,
        sortOption: SortOptionsDataModel
    ): PageDataModel<MovieDataModel> {
        val pageNumber = pageToRequest(page)
        val moviePage: PageResponseModel<MovieResponseModel> = when (sortOption) {
            SortOptionsDataModel.Popularity -> api.getPopular(pageNumber)
            SortOptionsDataModel.TopRated -> {
                throw UnsupportedOperationException("Not implemented yet.")
            }
            else -> api.getPopular(pageNumber)
        }
        return pageMapper.mapToPageDataModel(moviePage)
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        return movieMapper.mapToDataModel(api.getMovie(movieId))
    }
}
