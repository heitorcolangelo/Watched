package com.heitorcolangelo.data.local.movie.dao

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.local.common.DaoTest
import com.heitorcolangelo.data.local.factory.MovieEntityFactory
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class MovieDaoTest : DaoTest() {
    private val dao: MovieDao = database.getMovieDao()

    @Test
    fun when_getPagedMovies_then_returns_correct_page() {
        val list = MovieEntityFactory.makeList(30)
        val firstPage = list.subList(0, 10)
        val secondPage = list.subList(10, 20)
        val thirdPage = list.subList(20, 30)

        dao.saveMovies(list).test()

        val localData = TestLocalData()
        val pageSize = 10

        val firstPageOffset = localData.getOffset(pageSize, 0)
        dao.getPagedMovies(pageSize, firstPageOffset).test().assertValue {
            it.containsAll(firstPage)
        }

        val secondPageOffset = localData.getOffset(pageSize, 1)
        dao.getPagedMovies(pageSize, secondPageOffset).test().assertValue {
            it.containsAll(secondPage)
        }

        val thirdPageOffset = localData.getOffset(pageSize, 2)
        dao.getPagedMovies(pageSize, thirdPageOffset).test().assertValue {
            it.containsAll(thirdPage)
        }
    }

    @Test
    fun when_getMovie_then_returns_correct_movie() {
        val movie = MovieEntityFactory.make()
        val movieId = movie.id
        dao.saveMovies(listOf(movie)).test()

        dao.getMovie(movieId).test().assertValue(movie)
    }

    @Test
    fun when_clearMovies_then_clears_movie_table() {
        dao.saveMovies(MovieEntityFactory.makeList(20)).test()

        dao.clearMovies().test().assertComplete()

        dao.getMovies().test().assertValue { it.isEmpty() }
    }

    @Test
    fun when_saveMovies_then_save_it_to_db() {
        val movie = MovieEntityFactory.make()
        dao.saveMovies(listOf(movie)).test().assertComplete()

        dao.getMovies().test().assertValue {
            it.size == 1 && it[0] == movie
        }
    }

    @Test
    fun when_saveMovies_and_hasConflict_then_replace() {
        val movie = MovieEntityFactory.make()
        dao.saveMovies(listOf(movie)).test().assertComplete()

        val newTitle = "newTitle"
        val updatedMovie = movie.copy(title = newTitle)

        dao.saveMovies(listOf(updatedMovie)).test().assertComplete()

        dao.getMovies().test().assertValue {
            it.size == 1 && it[0] == updatedMovie
        }
    }

    private class TestLocalData : LocalData {
        override val dataConfigId: String
            get() = ""

        override fun clear(): Completable = Completable.complete()

        override fun setLastCacheTime(lastCacheTime: Long): Completable = Completable.complete()

        override fun isCacheExpired(currentTime: Long): Observable<Boolean> = Observable.just(false)

        override fun isDataCached(): Single<Boolean> = Single.just(true)
    }
}