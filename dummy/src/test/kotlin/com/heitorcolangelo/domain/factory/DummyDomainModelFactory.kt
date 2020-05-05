package com.heitorcolangelo.domain.factory

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel

internal object DummyFactory : MockFactory<DummyDomainModel> {
    override fun make(): DummyDomainModel {
        return DummyDomainModel(randomId())
    }
}
