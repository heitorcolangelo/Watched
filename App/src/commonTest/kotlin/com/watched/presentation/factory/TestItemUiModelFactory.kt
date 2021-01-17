package com.watched.presentation.factory

import com.watched.common.test.MockFactory

object TestItemUiModelFactory : MockFactory<TestItemUiModel> {
    override fun make(): TestItemUiModel {
        return TestItemUiModel(randomId())
    }
}
