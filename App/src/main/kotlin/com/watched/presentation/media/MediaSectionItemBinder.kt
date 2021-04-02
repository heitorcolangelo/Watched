package com.watched.presentation.media

import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.watched.databinding.ItemMediaBinding
import com.watched.databinding.ItemMediaSectionBinding
import com.watched.presentation.common.list.BaseAdapter
import com.watched.presentation.common.list.HorizontalItemDecoration
import com.watched.presentation.media.model.MediaItemUiModel
import com.watched.presentation.media.model.MediaSectionItemUiModel

class MediaSectionItemBinder :
    BaseAdapter.Binder<ItemMediaSectionBinding, MediaSectionItemUiModel> {

    override fun bindListItem(binding: ItemMediaSectionBinding, model: MediaSectionItemUiModel) {
        with(binding) {
            tvTitle.setText(model.title)

            val context = root.context
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rvList.layoutManager = layoutManager
            rvList.addItemDecoration(HorizontalItemDecoration(context))
            val adapter = BaseAdapter(ItemMediaBinding::inflate, MovieItemBinder())
            rvList.adapter = adapter

            adapter.submitList(model.list)
        }
    }

    class MovieItemBinder : BaseAdapter.Binder<ItemMediaBinding, MediaItemUiModel> {
        override fun bindListItem(binding: ItemMediaBinding, model: MediaItemUiModel) {
            binding.mediaPoster.load(model.poster.path()) {
                crossfade(true)
                transformations(RoundedCornersTransformation())
            }
        }
    }
}
