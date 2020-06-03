package com.heitorcolangelo.presentation.common.model

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.domain.common.model.PageDomainModel
import javax.inject.Inject

class PageDomainUiMapper<Domain : DomainModel, Ui : ItemUiModel> @Inject constructor(
    private val itemDomainUiMapper: DomainUiMapper<Domain, Ui>
) : DomainUiMapper<PageDomainModel<Domain>, PageUiModel<Ui>> {
    override fun mapToUiModel(domainModel: PageDomainModel<Domain>): PageUiModel<Ui> {
        return with(domainModel) {
            PageUiModel(
                items = items.map(itemDomainUiMapper::mapToUiModel),
                error = null
            )
        }
    }

    /**
     * Use this method when the request for a new page returned a error.
     *
     * TODO: Implement an DomainModel error class
     */
    fun mapToPageWithErrorUiModel(throwable: Throwable): PageUiModel<Ui> {
        return PageUiModel(
            items = listOf(),
            error = ErrorUiModel(throwable.localizedMessage.orEmpty(), "500")
        )
    }
}
