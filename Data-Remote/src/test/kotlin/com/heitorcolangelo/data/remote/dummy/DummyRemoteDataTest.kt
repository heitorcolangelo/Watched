package com.heitorcolangelo.data.remote.dummy

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummiesResponseModelMapper
import com.heitorcolangelo.data.remote.factory.DummiesResponseFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Test

class DummyRemoteDataTest {

    private val dummyApi: DummyApiService = mockk(relaxed = true)
    private val mapper: DummiesResponseModelMapper = mockk(relaxed = true)
    private val remoteData: DummyRemoteData = DummyRemoteDataImpl(dummyApi, mapper)

    @Test
    fun `WHEN get dummies THEN get dummies from api`() {
        remoteData.getDummies().test()

        verify {
            dummyApi.getDummies()
        }
    }

    @Test
    fun `WHEN get dummies THEN map to entities`() {
        every { dummyApi.getDummies() } returns Observable.just(DummiesResponseFactory.make())

        remoteData.getDummies().test()

        verify {
            mapper.mapToEntities(any())
        }
    }
}
