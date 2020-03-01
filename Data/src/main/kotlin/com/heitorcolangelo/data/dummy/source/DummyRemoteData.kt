package com.heitorcolangelo.data.dummy.source

import com.heitorcolangelo.data.common.source.RemoteData
import com.heitorcolangelo.data.dummy.entity.DummyEntity
import io.reactivex.Observable

interface DummyRemoteData : RemoteData {
    fun getDummies(): Observable<List<DummyEntity>>
}
