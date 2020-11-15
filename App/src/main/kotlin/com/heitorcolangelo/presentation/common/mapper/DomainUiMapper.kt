package com.heitorcolangelo.presentation.common.mapper

import com.heitorcolangelo.domain.common.model.DomainModel
import com.heitorcolangelo.presentation.common.model.UiModel

interface DomainUiMapper<in Domain : DomainModel, out Ui : UiModel> {
    fun mapToUiModel(domainModel: Domain): Ui
}
