package com.heitorcolangelo.data.remote.dummy

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummiesResponseDataMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DummyRemoteDataImpl @Inject constructor(
    private val dummyApi: DummyApiService,
    private val mapper: DummiesResponseDataMapper
) : DummyRemoteData {
    override fun getDummies(): Observable<List<DummyDataModel>> {
        return dummyApi.getDummies().map(mapper::mapToDataModelList)
    }
}
