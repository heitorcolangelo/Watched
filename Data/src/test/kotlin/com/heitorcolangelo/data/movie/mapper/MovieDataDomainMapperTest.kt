package com.heitorcolangelo.data.movie.mapper

import com.heitorcolangelo.data.factory.MovieDataModelFactory
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import org.junit.Assert.assertEquals
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
        assertEquals(dataModel.posterPath, domainModel.posterPath)
        assertEquals(dataModel.backdropPath, domainModel.backdropPath)
        assertEquals(dataModel.voteAverage, domainModel.voteAverage)
        assertEquals(dataModel.releaseDate, domainModel.releaseDate)
    }
}
