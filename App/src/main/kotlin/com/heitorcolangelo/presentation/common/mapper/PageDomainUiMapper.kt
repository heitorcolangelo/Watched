package com.heitorcolangelo.presentation.common.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.presentation.common.model.ItemUiModel
import javax.inject.Inject

interface PageDomainUiMapper<Domain : DomainModel, Ui : ItemUiModel> {
    fun mapToUiModel(pagingData: PagingData<Domain>): PagingData<Ui>
}

class PageDomainUiMapperImpl<Domain : DomainModel, Ui : ItemUiModel> @Inject constructor(
    private val itemDomainUiMapper: DomainUiMapper<Domain, Ui>
) : PageDomainUiMapper<Domain, Ui> {
    override fun mapToUiModel(pagingData: PagingData<Domain>): PagingData<Ui> {
        return pagingData.map(itemDomainUiMapper::mapToUiModel)
    }
}
