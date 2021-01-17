package com.watched.presentation.common.mapper

import com.watched.domain.common.model.DomainModel
import com.watched.presentation.common.model.UiModel

interface DomainUiMapper<in Domain : DomainModel, out Ui : UiModel> {
    fun mapToUiModel(domainModel: Domain): Ui
}
