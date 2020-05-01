package com.heitorcolangelo.presentation.common.model

import com.heitorcolangelo.domain.common.model.DomainModel

interface DomainUiMapper<Domain : DomainModel, Ui : UiModel> {
    fun mapToUiModel(domainModel: Domain): Ui
    fun mapToDomainModel(uiModel: Ui): Domain
}
