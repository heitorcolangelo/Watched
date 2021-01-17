package com.watched.data.common.source

interface LocalDataSource {

    companion object {
        private const val ONE_HOUR_IN_MILLI = (36 * 100 * 1000).toLong()
        const val cacheExpirationTime = ONE_HOUR_IN_MILLI
    }

    val dataConfigId: String

    suspend fun clear()

    suspend fun setLastCacheTime(lastCacheTime: Long)

    suspend fun isCacheExpired(currentTime: Long): Boolean

    suspend fun isDataCached(): Boolean

    suspend fun isCacheValid(currentTime: Long): Boolean {
        return isDataCached() && !isCacheExpired(currentTime)
    }

    fun getOffset(page: Int, pageSize: Int): Int {
        return (page * pageSize)
    }
}
