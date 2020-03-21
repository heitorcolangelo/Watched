package com.heitorcolangelo.data.common.source

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

interface LocalData {
    fun clear(): Completable

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isCacheExpired(currentTime: Long): Single<Boolean>

    fun isDataCached(): Single<Boolean>

    fun isCacheValid(currentTime: Long): Observable<Boolean> {
        return Observable.zip(
            isDataCached().toObservable(),
            isCacheExpired(currentTime).toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isDataCached, isCacheExpired ->
                Pair(
                    isDataCached,
                    isCacheExpired
                )
            }
        ).map {
            it.first && !it.second
        }
    }
}
