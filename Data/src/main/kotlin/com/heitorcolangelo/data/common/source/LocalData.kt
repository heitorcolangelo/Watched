package com.heitorcolangelo.data.common.source

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

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
