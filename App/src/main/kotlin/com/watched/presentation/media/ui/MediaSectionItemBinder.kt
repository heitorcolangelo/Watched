package com.watched.presentation.media

import coil.load
import coil.transform.RoundedCornersTransformation
import com.watched.databinding.ItemMediaBinding
import com.watched.databinding.ItemMediaSectionBinding
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.list.HorizontalItemDecoration
import com.watched.presentation.common.list.HorizontalLinearLayoutManager
import com.watched.presentation.media.model.MediaItemUiModel
import com.watched.presentation.media.model.MediaSectionItemUiModel

class MediaSectionItemBinder :
    BaseAdapter.Binder<ItemMediaSectionBinding, MediaSectionItemUiModel> {

    override fun bindListItem(binding: ItemMediaSectionBinding, model: MediaSectionItemUiModel) {
        with(binding) {
            tvTitle.setText(model.title)

            val context = root.context
            val adapter = BaseAdapter(ItemMediaBinding::inflate, MovieItemBinder())

            rvList.layoutManager = HorizontalLinearLayoutManager(context)
            rvList.addItemDecoration(HorizontalItemDecoration(context))
            rvList.adapter = adapter

            adapter.submitList(model.list)
        }
    }

    class MovieItemBinder : BaseAdapter.Binder<ItemMediaBinding, MediaItemUiModel> {
        override fun bindListItem(binding: ItemMediaBinding, model: MediaItemUiModel) {
            binding.mediaPoster.load(model.poster.path()) {
                crossfade(true)
                transformations(RoundedCornersTransformation(8f))
            }
        }
    }
}
