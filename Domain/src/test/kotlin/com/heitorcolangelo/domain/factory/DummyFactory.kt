package com.heitorcolangelo.domain.factory

import com.heitorcolangelo.domain.dummy.model.DummyDomainModel

object DummyFactory : MockFactory<DummyDomainModel> {
    override fun make(): DummyDomainModel {
        return DummyDomainModel(randomUuid())
    }
}
