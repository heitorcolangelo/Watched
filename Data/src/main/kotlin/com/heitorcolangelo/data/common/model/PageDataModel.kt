package com.heitorcolangelo.data.common.model

class PageDataModel<Model : DataModel>(
    val items: List<Model> = listOf(),
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalPages: Int = 0,
    val totalItems: Int = 0
) : DataModel()
