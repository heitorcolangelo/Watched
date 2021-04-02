package com.watched.movie.ui.mapper

import com.watched.movie.domain.model.MovieTopXDomainModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.media.mapper.MediaTopXPosterDomainUiMapper
import com.watched.presentation.media.model.MediaTopXUiModel
import javax.inject.Inject

class MovieTopXDomainUiMapper @Inject constructor(
    private val posterMapper: MediaTopXPosterDomainUiMapper
) : DomainUiMapper<MovieTopXDomainModel, MediaTopXUiModel> {

    override fun mapToUiModel(domainModel: MovieTopXDomainModel): MediaTopXUiModel {
        return with(domainModel) {
            MediaTopXUiModel(
                id = movie.id,
                poster = posterMapper.mapToUiModel(movie.poster),
                position = position
            )
        }
    }
}
