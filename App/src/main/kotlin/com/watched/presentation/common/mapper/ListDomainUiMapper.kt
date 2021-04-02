package com.watched.presentation.common.mapper

import com.watched.domain.common.model.DomainModel
import com.watched.presentation.common.model.ItemUiModel
import javax.inject.Inject

interface ListDomainUiMapper<Domain : DomainModel, Ui : ItemUiModel> {
    fun mapToUiModel(domainModel: List<Domain>): List<Ui>
}

class ListDomainUiMapperImpl<Domain : DomainModel, Ui : ItemUiModel> @Inject constructor(
    private val itemMapper: DomainUiMapper<Domain, Ui>
) : ListDomainUiMapper<Domain, Ui> {
    override fun mapToUiModel(domainModel: List<Domain>): List<Ui> {
        return domainModel.map(itemMapper::mapToUiModel)
    }
}
