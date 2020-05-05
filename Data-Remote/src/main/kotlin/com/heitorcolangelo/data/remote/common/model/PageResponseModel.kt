package com.heitorcolangelo.data.remote.common.model

import com.squareup.moshi.Json

class PageResponseModel<Response : ResponseModel>(
    @Json(name = "results") val results: List<Response> = listOf(),
    @Json(name = "page") val page: Int = 0,
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_results") val totalResults: Int = 0
) : ResponseModel
