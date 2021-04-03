package com.watched.presentation.media.mapper

import com.watched.R
import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.domain.media.MediaDomainModel
import com.watched.domain.media.SortedMediaDomainModel
import com.watched.presentation.common.mapper.DomainUiMapper
import com.watched.presentation.common.mapper.ListDomainUiMapper
import com.watched.presentation.media.ui.model.MediaItemUiModel
import com.watched.presentation.media.ui.model.MediaSectionItemUiModel
import javax.inject.Inject

class MediaSectionDomainUiMapper @Inject constructor(
    private val mediaListMapper: ListDomainUiMapper<MediaDomainModel, MediaItemUiModel>
) : DomainUiMapper<SortedMediaDomainModel, MediaSectionItemUiModel> {

    override fun mapToUiModel(domainModel: SortedMediaDomainModel): MediaSectionItemUiModel {
        return MediaSectionItemUiModel(
            id = domainModel.toString(),
            title = getSectionTitle(domainModel.sortOptions),
            list = mediaListMapper.mapToUiModel(domainModel.items)
        )
    }

    private fun getSectionTitle(sortOptionsDomainModel: SortOptionsDomainModel?): Int {
        return when (sortOptionsDomainModel) {
            SortOptionsDomainModel.Popularity -> R.string.popular_media
            SortOptionsDomainModel.TopRated -> R.string.top_rated_media
            else -> R.string.no_sort_option_media
        }
    }
}
