package com.watched.presentation.media

import coil.load
import com.watched.R
import com.watched.databinding.ItemMediaTopXBinding
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.media.model.MediaTopXUiModel

class MediaTopXItemBinder : BaseAdapter.Binder<ItemMediaTopXBinding, MediaTopXUiModel> {
    override fun bindListItem(binding: ItemMediaTopXBinding, model: MediaTopXUiModel) {
        with(binding) {
            mediaPoster.load(model.poster.path) {
                crossfade(true)
            }
            positionText.text = root.context.getString(R.string.top_x_today, model.position)
        }
    }
}