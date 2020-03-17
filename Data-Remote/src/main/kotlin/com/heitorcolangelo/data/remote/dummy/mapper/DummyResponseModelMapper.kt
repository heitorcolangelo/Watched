package com.heitorcolangelo.data.remote.dummy.mapper

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.remote.common.mapper.ResponseDataModelMapper
import com.heitorcolangelo.data.remote.common.mapper.ResponseListDataModelMapper
import com.heitorcolangelo.data.remote.dummy.model.DummiesResponseModel
import com.heitorcolangelo.data.remote.dummy.model.DummyResponseModel
import javax.inject.Inject
import javax.inject.Singleton

object DummyResponseModelMapper : ResponseDataModelMapper<DummyResponseModel, DummyEntity> {
    override fun mapToEntity(response: DummyResponseModel): DummyEntity {
        return DummyEntity(response.dummyId)
    }
}

@Singleton
class DummiesResponseModelMapper @Inject constructor(
    private val mapper: DummyResponseModelMapper
) : ResponseListDataModelMapper<DummiesResponseModel, DummyEntity> {
    override fun mapToEntities(response: DummiesResponseModel): List<DummyEntity> {
        return response.dummies.map {
            mapper.mapToEntity(it)
        }
    }
}
