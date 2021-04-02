package com.watched.data.local.factory

import com.watched.common.test.MockFactory
import com.watched.data.local.config.entity.ConfigEntity

internal object ConfigEntityFactory : MockFactory<ConfigEntity> {
    override fun make(): ConfigEntity {
        return ConfigEntity(randomId(), randomLong())
    }
}
