package com.watched.data.remote.movie.api

import com.watched.data.remote.common.api.ApiService
import com.watched.data.remote.common.model.PageResponseModel
import com.watched.data.remote.movie.model.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    companion object {
        const val BASE_URL = "${ApiService.BASE_URL}movie/"
    }

    @GET("popular")
    suspend fun getPopular(@Query("page") page: Int = 1): PageResponseModel<MovieResponseModel>

    @GET("{movieId}")
    suspend fun getMovie(@Path("movieId") movieId: String): MovieResponseModel

    @GET("latest")
    suspend fun getLatestMovie(): MovieResponseModel?
}
