package com.heitorcolangelo.domain.dummy.model

import com.heitorcolangelo.domain.common.model.DomainModel

data class DummyDomainModel(val dummyId: String) : DomainModel(dummyId)

data class DummiesDomainModel(
    val dummyId: String,
    val dummyList: List<DummyDomainModel>
) : DomainModel(dummyId)
