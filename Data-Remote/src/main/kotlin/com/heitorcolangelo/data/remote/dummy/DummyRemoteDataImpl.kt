package com.heitorcolangelo.data.remote.dummy

import com.heitorcolangelo.data.dummy.entity.DummyEntity
import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummiesResponseModelMapper
import io.reactivex.Observable
import javax.inject.Inject

class DummyRemoteDataImpl @Inject constructor(
    private val dummyApi: DummyApiService,
    private val mapper: DummiesResponseModelMapper
) : DummyRemoteData {
    override fun getDummies(): Observable<List<DummyEntity>> {
        return dummyApi.getDummies().map(mapper::mapToEntities)
    }
}
