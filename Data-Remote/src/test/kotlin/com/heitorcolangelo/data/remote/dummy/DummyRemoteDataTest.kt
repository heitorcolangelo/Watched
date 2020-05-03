package com.heitorcolangelo.data.remote.dummy

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummyResponseDataListMapper
import com.heitorcolangelo.data.remote.factory.DummiesResponseFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class DummyRemoteDataTest {

    private val dummyApi: DummyApiService = mockk(relaxed = true)
    private val listMapper: DummyResponseDataListMapper = mockk(relaxed = true)
    private val remoteData: DummyRemoteData = DummyRemoteDataImpl(dummyApi, listMapper)

    @Test
    fun `WHEN get dummies THEN get dummies from api`() {
        remoteData.getDummies().test()

        verify {
            dummyApi.getDummies()
        }
    }

    @Test
    fun `WHEN get dummies THEN map to data model list`() {
        every { dummyApi.getDummies() } returns Observable.just(DummiesResponseFactory.make())

        remoteData.getDummies().test()

        verify {
            listMapper.mapToDataModelList(any())
        }
    }
}
