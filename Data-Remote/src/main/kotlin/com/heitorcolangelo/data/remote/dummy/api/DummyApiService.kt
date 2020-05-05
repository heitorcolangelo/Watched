package com.heitorcolangelo.data.remote.dummy.api

import com.heitorcolangelo.data.remote.common.api.ApiService
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface DummyApiService {
    companion object {
        const val BASE_URL = "${ApiService.BASE_URL}/dummy/"
    }

    @GET("/dummies")
    fun getDummies(): Observable<List<DummyResponseModel>>
}
