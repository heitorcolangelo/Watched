package com.heitorcolangelo.data.remote.movie.api

import com.heitorcolangelo.data.remote.common.api.ApiService
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.reactivex.rxjava3.core.Observable
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
    fun getMovie(@Path("movieId") movieId: String): Observable<MovieResponseModel>

    @GET("latest")
    suspend fun getLatestMovie(): MovieResponseModel
}
