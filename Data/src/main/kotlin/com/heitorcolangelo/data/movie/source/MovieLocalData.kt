package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface MovieLocalData : LocalData {
    override val dataConfigId: String
        get() = MovieLocalData::class.java.name

    fun saveMovies(movies: List<MovieDataModel>): Completable

    fun getMovies(): Observable<List<MovieDataModel>>
}
