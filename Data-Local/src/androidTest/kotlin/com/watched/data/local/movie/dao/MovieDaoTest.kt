package com.watched.data.local.movie.dao

import com.watched.data.common.source.LocalDataSource
import com.watched.data.local.common.DaoTest
import com.watched.data.local.factory.MovieEntityFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MovieDaoTest : DaoTest() {
    private val dao: MovieDao = database.getMovieDao()

    @Test
    fun when_getPagedMovies_then_returns_correct_page() {
        val list = MovieEntityFactory.makeList(30)
        val firstPage = list.subList(0, 10)
        val secondPage = list.subList(10, 20)
        val thirdPage = list.subList(20, 30)

        runBlocking { dao.saveMovies(list) }

        val localData = TestLocalDataSource()
        val pageSize = 10

        val firstPageOffset = localData.getOffset(pageSize, 0)
        runBlocking {
            dao.getPagedMovies(pageSize, firstPageOffset).containsAll(firstPage)
        }

        val secondPageOffset = localData.getOffset(pageSize, 1)
        runBlocking {
            dao.getPagedMovies(pageSize, secondPageOffset).containsAll(secondPage)
        }

        val thirdPageOffset = localData.getOffset(pageSize, 2)
        runBlocking {
            dao.getPagedMovies(pageSize, thirdPageOffset).containsAll(thirdPage)
        }
    }

    @Test
    fun when_getMovie_then_returns_correct_movie() {
        val movie = MovieEntityFactory.make()
        val movieId = movie.id
        runBlocking { dao.saveMovies(listOf(movie)) }

        runBlocking {
            assertEquals(dao.getMovie(movieId), movie)
        }
    }

    @Test
    fun when_clearMovies_then_clears_movie_table(): Unit = runBlocking {
        dao.saveMovies(MovieEntityFactory.makeList(20))

        dao.clearMovies()

        assertTrue(dao.getMovies().isEmpty())
    }

    @Test
    fun when_saveMovies_then_save_it_to_db(): Unit = runBlocking {
        val movie = MovieEntityFactory.make()
        dao.saveMovies(listOf(movie))

        val movies = dao.getMovies()
        assertEquals(1, movies.size)
        assertEquals(movie, movies[0])
    }

    @Test
    fun when_saveMovies_and_hasConflict_then_replace(): Unit = runBlocking {
        val movie = MovieEntityFactory.make()
        dao.saveMovies(listOf(movie))

        val newTitle = "newTitle"
        val updatedMovie = movie.copy(title = newTitle)

        dao.saveMovies(listOf(updatedMovie))

        val movies = dao.getMovies()
        assertEquals(1, movies.size)
        assertEquals(updatedMovie, movies[0])
    }

    private class TestLocalDataSource : LocalDataSource {
        override val dataConfigId: String
            get() = ""

        override suspend fun clear() {
        }

        override suspend fun setLastCacheTime(lastCacheTime: Long) {
        }

        override suspend fun isCacheExpired(currentTime: Long): Boolean = false

        override suspend fun isDataCached(): Boolean = true
    }
}
