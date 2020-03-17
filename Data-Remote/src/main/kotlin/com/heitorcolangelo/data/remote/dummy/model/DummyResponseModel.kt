package com.heitorcolangelo.data.remote.dummy.model

import com.heitorcolangelo.data.remote.common.model.ResponseModel
import com.squareup.moshi.Json

data class DummyResponseModel(
    @field:Json(name = "id") val dummyId: String
) : ResponseModel(dummyId)

data class DummiesResponseModel(
    @field:Json(name = "values") val dummies: List<DummyResponseModel>
) : ResponseModel()
