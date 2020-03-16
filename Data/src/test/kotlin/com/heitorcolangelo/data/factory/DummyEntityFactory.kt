package com.heitorcolangelo.data.factory

import com.heitorcolangelo.data.dummy.entity.DummyEntity

internal object DummyEntityFactory : MockFactory<DummyEntity> {
    override fun make(): DummyEntity {
        return DummyEntity(randomId())
    }
}
