package com.heitorcolangelo.data.local.factory

import com.heitorcolangelo.common.test.MockFactory
import com.heitorcolangelo.data.local.config.entity.ConfigEntity

object ConfigEntityFactory : MockFactory<ConfigEntity> {
    override fun make(): ConfigEntity {
        return ConfigEntity(randomId(), randomLong())
    }
}
