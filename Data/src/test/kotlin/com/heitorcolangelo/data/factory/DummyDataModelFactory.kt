package com.heitorcolangelo.data.factory

import com.heitorcolangelo.data.dummy.model.DummyDataModel

object DummyDataModelFactory : MockFactory<DummyDataModel> {
    override fun make(): DummyDataModel {
        return DummyDataModel(randomId())
    }
}
