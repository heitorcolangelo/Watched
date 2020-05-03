package com.heitorcolangelo.data.common.source

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

interface LocalData {

    companion object {
        const val cacheExpirationTime = (36 * 100 * 1000).toLong()
    }

    val dataConfigId: String

    fun clear(): Completable

    fun setLastCacheTime(lastCacheTime: Long): Completable

    fun isCacheExpired(currentTime: Long): Observable<Boolean>

    fun isDataCached(): Single<Boolean>

    fun isCacheValid(currentTime: Long): Observable<Boolean> {
        return Observable.zip(
            isDataCached().toObservable(),
            isCacheExpired(currentTime),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> { isDataCached, isCacheExpired ->
                Pair(isDataCached, isCacheExpired)
            }
        ).map {
            it.first && !it.second
        }
    }
}
