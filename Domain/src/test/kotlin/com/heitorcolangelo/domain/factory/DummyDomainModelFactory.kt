package com.heitorcolangelo.domain.factory

import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.domain.dummy.model.DummyDomainModel

internal object DummyFactory : MockFactory<DummyDomainModel> {
    override fun make(): DummyDomainModel {
        return DummyDomainModel(randomId())
    }
}

internal object DummiesFactory : MockFactory<DummiesDomainModel> {
    private const val DEFAULT_DUMMY_LIST_ELEMENTS = 3

    override fun make(): DummiesDomainModel {
        return DummiesDomainModel(
            DummyFactory.makeList(DEFAULT_DUMMY_LIST_ELEMENTS)
        )
    }
}
