package com.heitorcolangelo.presentation.common.viewmodel

import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.presentation.common.model.ItemUiModel

open class PagedLiveData<Model : ItemUiModel> : MutableLiveData<List<Model>>() {
    override fun postValue(value: List<Model>?) {
        if (value.isNullOrEmpty()) {
            super.postValue(listOf())
        } else {
            val updatedValue = this.value.orEmpty().toMutableList().also {
                it.addAll(value)
            }
            super.postValue(updatedValue)
        }
    }
}
