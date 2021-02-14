package com.watched.domain.common.model

interface ListDomainModel<Domain : DomainModel> : DomainModel {
    val id: String
    val items: List<Domain>
}