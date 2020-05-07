package com.heitorcolangelo.data.local.factory

import com.heitorcolangelo.data.local.config.entity.ConfigEntity
import com.heitorcolangelo.test.common.MockFactory

object ConfigEntityFactory : MockFactory<ConfigEntity> {
    override fun make(): ConfigEntity {
        return ConfigEntity(randomId(), randomLong())
    }
}
