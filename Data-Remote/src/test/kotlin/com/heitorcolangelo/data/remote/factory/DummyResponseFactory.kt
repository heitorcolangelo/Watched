package com.heitorcolangelo.data.remote.factory

import com.heitorcolangelo.data.remote.dummy.model.DummiesResponseModel
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel

internal object DummyResponseFactory : MockFactory<DummyResponseModel> {
    override fun make(): DummyResponseModel {
        return DummyResponseModel(randomId())
    }
}

internal object DummiesResponseFactory : MockFactory<DummiesResponseModel> {
    const val DEFAULT_DUMMY_LIST_ELEMENTS = 3

    override fun make(): DummiesResponseModel {
        return DummiesResponseModel(
            DummyResponseFactory.makeList(3)
        )
    }
}
