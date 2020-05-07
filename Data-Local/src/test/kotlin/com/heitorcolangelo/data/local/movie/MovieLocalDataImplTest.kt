package com.heitorcolangelo.data.local.movie

import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.factory.MovieEntityFactory
import com.heitorcolangelo.data.local.movie.dao.MovieDao
import com.heitorcolangelo.data.local.movie.mapper.MovieEntityDataMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Flowable
import org.junit.Test

class MovieLocalDataImplTest {
    private val movieDao: MovieDao = mockk(relaxed = true)
    private val mapper: MovieEntityDataMapper = mockk(relaxed = true)
    private val configDao: ConfigDao = mockk(relaxed = true)
    private val localData = MovieLocalDataImpl(movieDao, mapper, configDao)

    @Test
    fun `WHEN dao return movies THEN data is cached`() {
        val movieList = MovieEntityFactory.makeList(3)
        every { movieDao.getMovies() } returns Flowable.just(movieList)

        val testObserver = localData.isDataCached().test()

        verify { movieDao.getMovies() }
        testObserver.assertValue(true)
    }

    @Test
    fun `WHEN dao DONT return movies THEN data is NOT cached`() {
        every { movieDao.getMovies() } returns Flowable.just(emptyList())

        val testObserver = localData.isDataCached().test()

        verify { movieDao.getMovies() }
        testObserver.assertValue(false)
    }

    @Test
    fun `WHEN save movies THEN map to entities`() {
        val moviesToSave = listOf(mockk<MovieDataModel>(relaxed = true))

        localData.saveMovies(moviesToSave).test()

        verify { mapper.mapToEntity(any()) }
    }

    @Test
    fun `WHEN save movies THEN save it to dao`() {
        val moviesToSave = listOf(mockk<MovieDataModel>(relaxed = true))
        val movieEntity = MovieEntityFactory.make()
        every { mapper.mapToEntity(any()) } returns movieEntity

        localData.saveMovies(moviesToSave).test()

        verify { movieDao.saveMovies(listOf(movieEntity)) }
    }

    @Test
    fun `WHEN get movies THEN get from dao`() {
        localData.getMovies().test()

        verify { movieDao.getMovies() }
    }

    @Test
    fun `WHEN get movies THEN map to data model`() {
        val movieList = MovieEntityFactory.makeList(3)
        every { movieDao.getMovies() } returns Flowable.just(movieList)

        localData.getMovies().test()

        verify(exactly = 3) { mapper.mapToDataModel(any()) }
    }

    @Test
    fun `WHEN clear THEN clear dao`() {
        localData.clear()

        verify { movieDao.clearMovies() }
    }
}
