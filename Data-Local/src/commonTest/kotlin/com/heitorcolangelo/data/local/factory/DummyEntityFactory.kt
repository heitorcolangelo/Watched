package com.heitorcolangelo.data.local.factory

import com.heitorcolangelo.data.local.dummy.entity.DummyEntity

object DummyEntityFactory : MockFactory<DummyEntity> {
    override fun make(): DummyEntity {
        return DummyEntity(randomId())
    }
}
