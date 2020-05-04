package com.heitorcolangelo.data.movie.mapper

import com.heitorcolangelo.data.factory.MovieDataModelFactory
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test

class PagedMovieDataDomainMapperTest {

    private val itemMapper: MovieDataDomainMapper = mockk(relaxed = true)
    private val mapper = PagedMovieDataDomainMapper(itemMapper)
    private val elements = 3

    @Test
    fun mapToDomainModel() {
        val domainModel = mapper.mapToDomainModel(MovieDataModelFactory.makeList(elements))

        verify(exactly = elements) { itemMapper.mapToDomainModel(any()) }
        assertEquals(domainModel.items.size, elements)
    }

    @Test
    fun mapToDataModelList() {
        val domainModel = mapper.mapToDomainModel(MovieDataModelFactory.makeList(elements))

        val dataModelList = mapper.mapToDataModelList(domainModel)

        verify(exactly = elements) { itemMapper.mapToDataModel(any()) }
        assertEquals(dataModelList.size, elements)
    }
}