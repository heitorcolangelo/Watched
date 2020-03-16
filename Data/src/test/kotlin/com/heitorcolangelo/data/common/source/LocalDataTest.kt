package com.heitorcolangelo.data.common.source

import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test

class LocalDataTest {

    @Test
    fun `WHEN data is cached AND cache is expired THEN cache is NOT valid`() {
        val localData = LocalDataImpl(isDataCached = true, isCacheExpired = true)

        val testObserver = localData.isCacheValid().test()

        testObserver.assertValue(false)
    }

    @Test
    fun `WHEN data is cached AND cache is NOT expired THEN cache is valid`() {
        val localData = LocalDataImpl(isDataCached = true, isCacheExpired = false)

        val testObserver = localData.isCacheValid().test()

        testObserver.assertValue(true)
    }

    @Test
    fun `WHEN data is not cached THEN cache is NOT valid`() {
        val localData = LocalDataImpl(isDataCached = false, isCacheExpired = true)

        val testObserver = localData.isCacheValid().test()

        testObserver.assertValue(false)
    }

    private class LocalDataImpl(
        private val isCacheExpired: Boolean,
        private val isDataCached: Boolean
    ) : LocalData {
        override fun clear() = Completable.complete()
        override fun setLastCacheTime(lastCacheTime: Long) = Completable.complete()
        override fun isCacheExpired() = Single.just(isCacheExpired)
        override fun isDataCached() = Single.just(isDataCached)
    }
}
