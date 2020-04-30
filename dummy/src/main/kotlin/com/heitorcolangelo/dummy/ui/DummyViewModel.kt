package com.heitorcolangelo.dummy.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.heitorcolangelo.domain.dummy.model.DummiesDomainModel
import com.heitorcolangelo.dummy.domain.GetDummiesUseCase
import com.heitorcolangelo.dummy.model.DummyDomainUiMapper
import com.heitorcolangelo.dummy.model.DummyUiModel
import com.heitorcolangelo.presentation.common.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class DummyViewModel constructor(
    private val mapper: DummyDomainUiMapper,
    private val useCase: GetDummiesUseCase
) : BaseViewModel(useCase) {

    private val _dummies = MutableLiveData<List<DummyUiModel>>()
    val dummies: LiveData<List<DummyUiModel>> = _dummies

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        useCase.execute(DummiesObserver(_dummies, mapper))
    }

    inner class DummiesObserver(
        private val dummiesLiveData: MutableLiveData<List<DummyUiModel>>,
        private val mapper: DummyDomainUiMapper
    ) : DisposableObserver<DummiesDomainModel>() {
        override fun onComplete() {
        }

        override fun onNext(dummies: DummiesDomainModel) {
            val dummiesUiModel = dummies.dummyList.map(mapper::mapToUiModel)
            dummiesLiveData.postValue(dummiesUiModel)
        }

        override fun onError(error: Throwable) {
            _error.postValue(error.localizedMessage)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val mapper: DummyDomainUiMapper,
        private val useCase: GetDummiesUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DummyViewModel(mapper, useCase) as T
        }
    }
}
