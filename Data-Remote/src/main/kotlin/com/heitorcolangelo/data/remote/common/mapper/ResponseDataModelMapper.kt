package com.heitorcolangelo.data.remote.common.mapper

import com.heitorcolangelo.data.common.entity.Entity
import com.heitorcolangelo.data.remote.common.model.ResponseModel

interface ResponseDataModelMapper<in RM : ResponseModel, out E : Entity> {
    fun mapToEntity(response: RM): E
}

interface ResponseListDataModelMapper<in RM : ResponseModel, out E : Entity> {
    fun mapToEntities(response: RM): List<E>
}
