package com.heitorcolangelo.data.remote.common.api

import javax.inject.Inject
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

class ApiServiceFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val converterFactory: Converter.Factory,
) {
    fun <ApiService> initService(serviceClass: Class<ApiService>, baseUrl: String): ApiService {
        synchronized(this) {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build()
                .create(serviceClass)
        }
    }
}
