package com.watched.data.local.movie

import com.watched.data.local.config.dao.ConfigDao
import com.watched.data.local.factory.MovieEntityFactory
import com.watched.data.local.movie.dao.MovieDao
import com.watched.data.local.movie.mapper.MovieEntityDataMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieLocalDataSourceImplTest {
    private val movieDao: MovieDao = mockk(relaxed = true)
    private val mapper: MovieEntityDataMapper = mockk(relaxed = true)
    private val configDao: ConfigDao = mockk(relaxed = true)
    private val localData = MovieLocalDataSourceImpl(movieDao, mapper, configDao)

    @Test
    fun `WHEN dao return movies THEN data is cached`() {
        val movieList = MovieEntityFactory.makeList(3)
        coEvery { movieDao.getMovies() } returns movieList

        val isCached = runBlocking { localData.isDataCached() }

        coVerify { movieDao.getMovies() }
        assertTrue(isCached)
    }

    @Test
    fun `WHEN dao DONT return movies THEN data is NOT cached`() {
        coEvery { movieDao.getMovies() } returns emptyList()

        val isCached = runBlocking { localData.isDataCached() }

        coVerify { movieDao.getMovies() }
        assertFalse(isCached)
    }

    @Test
    fun `WHEN save movies THEN map to entities`() {
        val moviesToSave = listOf(mockk<MovieDataModel>(relaxed = true))

        runBlocking { localData.saveMovies(moviesToSave) }

        coVerify { mapper.mapToEntity(any()) }
    }

    @Test
    fun `WHEN save movies THEN save it to dao`() {
        val moviesToSave = listOf(mockk<MovieDataModel>(relaxed = true))
        val movieEntity = MovieEntityFactory.make()
        every { mapper.mapToEntity(any()) } returns movieEntity

        runBlocking { localData.saveMovies(moviesToSave) }

        coVerify { movieDao.saveMovies(listOf(movieEntity)) }
    }

    @Test
    fun `WHEN get movies THEN get pagedMovies from dao`() {
        val movieList = MovieEntityFactory.makeList(3)
        coEvery { movieDao.getPagedMovies(any(), any()) } returns movieList
        val page = 1
        val pageSize = 10
        runBlocking { localData.getMovies(page, pageSize, SortOptionsDataModel.Popularity) }

        val offset = localData.getOffset(page, pageSize)

        coVerify { movieDao.getPagedMovies(pageSize, offset) }
    }

    @Test
    fun `WHEN get movies THEN map to data model`() {
        val movieList = MovieEntityFactory.makeList(3)
        coEvery { movieDao.getPagedMovies(any(), any()) } returns movieList
        val page = 1
        val pageSize = 10

        runBlocking { localData.getMovies(page, pageSize, SortOptionsDataModel.Popularity) }

        verify(exactly = 3) { mapper.mapToDataModel(any()) }
    }

    @Test
    fun `WHEN clear THEN clear dao`() {
        runBlocking { localData.clear() }

        coVerify { movieDao.clearMovies() }
    }

    @Test
    fun `WHEN get movie THEN map to data model`() {
        val movie = MovieEntityFactory.make()
        val movieId = movie.id
        coEvery { movieDao.getMovie(movieId) } returns movie

        runBlocking { localData.getMovie(movieId) }

        coVerify { mapper.mapToDataModel(movie) }
    }

    @Test
    fun `WHEN get movie THEN get from dao`() {
        val movie = MovieEntityFactory.make()
        val movieId = movie.id
        coEvery { movieDao.getMovie(movieId) } returns movie

        runBlocking { localData.getMovie(movieId) }

        coVerify { movieDao.getMovie(movieId) }
    }
}
