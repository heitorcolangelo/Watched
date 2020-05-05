package com.heitorcolangelo.domain.movie.model

sealed class MoviesSortOption {
    object Popularity : MoviesSortOption()
    object TopRated : MoviesSortOption()
    object MostRecent : MoviesSortOption()
}
