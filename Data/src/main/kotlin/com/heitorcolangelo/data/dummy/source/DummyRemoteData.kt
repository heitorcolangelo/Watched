package com.heitorcolangelo.data.dummy.source

import com.heitorcolangelo.data.common.source.RemoteData
import com.heitorcolangelo.data.dummy.model.DummyDataModel
import io.reactivex.rxjava3.core.Observable

interface DummyRemoteData : RemoteData {
    fun getDummies(): Observable<List<DummyDataModel>>
}
