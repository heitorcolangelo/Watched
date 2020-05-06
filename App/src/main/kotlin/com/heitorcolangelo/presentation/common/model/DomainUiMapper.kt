package com.heitorcolangelo.presentation.common.model

import com.heitorcolangelo.domain.common.model.DomainModel

interface DomainUiMapper<in Domain : DomainModel, out Ui : UiModel> {
    fun mapToUiModel(domainModel: Domain): Ui
}
