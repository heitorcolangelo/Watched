package com.watched.domain.common.model

data class ListDomainModel<Domain: DomainModel>(
    val id: String,
    val items: List<Domain>
): DomainModel
