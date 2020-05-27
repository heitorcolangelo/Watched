package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.source.RemoteData
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Observable

interface MovieRemoteData : RemoteData {
    fun getMovies(page: Int): Observable<PageDataModel<MovieDataModel>>
    fun getMovie(movieId: String): Observable<MovieDataModel>

    /**
     * This is weird, but the API pagination starts with 1.
     */
    fun getNextPage(page: Int): Int {
        return page + 1
    }
}
