package com.heitorcolangelo.data.remote.common.model

const val NO_ID = "NO_ID"

open class ResponseModel(val id: String = NO_ID)
open class RequestModel(val id: String = NO_ID)
