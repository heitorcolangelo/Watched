package com.heitorcolangelo.data.remote.movie.api

import com.heitorcolangelo.data.remote.common.api.ApiService
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MovieApiService {
    companion object {
        const val BASE_URL = "${ApiService.BASE_URL}movie/"
    }

    @GET("popular")
    fun getPopular(): Observable<PageResponseModel<MovieResponseModel>>
}
