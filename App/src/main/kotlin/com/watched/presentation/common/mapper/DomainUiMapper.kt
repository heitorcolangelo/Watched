package com.watched.presentation.common.mapper

import com.watched.domain.common.model.DomainModel
import com.watched.presentation.common.model.UiModel

interface DomainUiMapper<Domain : DomainModel, Ui : UiModel> {
    fun mapToUiModel(domainModel: Domain): Ui
}
