package com.heitorcolangelo.domain.common.model

data class PageDomainModel<Model : DomainModel>(
    val items: List<Model> = listOf(),
    val page: Int = 0,
    val pageSize: Int = 0,
    val totalPages: Int = 0,
    val totalItems: Int = 0
) : DomainModel() {
    companion object {
        const val FIRST_PAGE = 0
    }
}
