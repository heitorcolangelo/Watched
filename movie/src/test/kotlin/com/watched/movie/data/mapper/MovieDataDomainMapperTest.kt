package com.watched.movie.data.mapper

import com.watched.domain.common.model.RawDateDomainModel
import com.watched.movie.data.factory.MovieDataModelFactory
import com.watched.movie.data.model.MovieDataModel
import com.watched.movie.domain.mapper.MovieDataDomainMapper
import com.watched.movie.domain.model.MovieDomainModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieDataDomainMapperTest {

    private val mapper = MovieDataDomainMapper()

    @Test
    fun mapToDomainModel() {
        val dataModel = MovieDataModelFactory.make()

        val domainModel = mapper.mapToDomainModel(dataModel)

        assertContent(dataModel, domainModel)
    }

    @Test
    fun mapToDataModel() {
        val domainModel = mapper.mapToDomainModel(MovieDataModelFactory.make())

        val dataModel = mapper.mapToDataModel(domainModel)

        assertContent(dataModel, domainModel)
    }

    private fun assertContent(dataModel: MovieDataModel, domainModel: MovieDomainModel) {
        assertEquals(dataModel.id, domainModel.id)
        assertEquals(dataModel.title, domainModel.title)
        assertEquals(dataModel.overview, domainModel.overview)
        assertEquals(dataModel.popularity, domainModel.popularity)
        assertEquals(dataModel.posterPath, domainModel.poster.path)
        assertEquals(dataModel.backdropPath, domainModel.backdrop.path)
        assertEquals(dataModel.voteAverage, domainModel.voteAverage)
        val releaseDate = RawDateDomainModel(dataModel.releaseDate)
        assertTrue(releaseDate == domainModel.releaseDate)
    }
}
