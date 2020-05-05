package com.heitorcolangelo.data.remote.dummy.model

import com.heitorcolangelo.data.remote.common.model.ResponseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DummyResponseModel(
    @field:Json(name = "id") val dummyId: String
) : ResponseModel
