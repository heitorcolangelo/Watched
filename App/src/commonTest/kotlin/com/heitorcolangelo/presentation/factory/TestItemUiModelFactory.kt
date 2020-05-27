package com.heitorcolangelo.presentation.factory

import com.heitorcolangelo.common.test.MockFactory

object TestItemUiModelFactory : MockFactory<TestItemUiModel> {
    override fun make(): TestItemUiModel {
        return TestItemUiModel(randomId())
    }
}
