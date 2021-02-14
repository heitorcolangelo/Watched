package com.watched.presentation.common.mapper

import com.watched.domain.common.model.DomainModel
import com.watched.domain.common.model.ListDomainModel
import com.watched.presentation.common.model.ItemUiModel
import com.watched.presentation.common.model.ListUiModel
import javax.inject.Inject

interface ListDomainUiMapper<Domain : DomainModel, Ui : ItemUiModel> {
    fun mapToUiModel(domainModel: ListDomainModel<Domain>): ListUiModel<Ui>
}

class ListDomainUiMapperImpl<Domain : DomainModel, Ui : ItemUiModel> @Inject constructor(
    private val itemMapper: DomainUiMapper<Domain, Ui>
) : ListDomainUiMapper<Domain, Ui> {
    override fun mapToUiModel(domainModel: ListDomainModel<Domain>): ListUiModel<Ui> {
        return object : ListUiModel<Ui> {
            override val id: String
                get() = domainModel.toString()
            override val items: List<Ui>
                get() = domainModel.items.map(itemMapper::mapToUiModel)
        }
    }
}
