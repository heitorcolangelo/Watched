package com.heitorcolangelo.presentation.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.presentation.common.model.ItemUiModel
import com.heitorcolangelo.presentation.common.model.PageUiModel

class PagedLiveData<Model : ItemUiModel> : MutableLiveData<PageUiModel<Model>>() {
    fun refreshList() {
        this.postValue(PageUiModel(listOf(), null))
    }
}
