package com.heitorcolangelo.data.common.source

import io.reactivex.Completable
import io.reactivex.Single

interface LocalData {
    fun clear(): Completable

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isCacheExpired(): Single<Boolean>

    fun isDataCached(): Single<Boolean>
}
