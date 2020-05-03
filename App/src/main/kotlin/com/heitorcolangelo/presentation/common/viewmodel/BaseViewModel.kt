package com.heitorcolangelo.presentation.common.viewmodel

import androidx.lifecycle.ViewModel
import com.heitorcolangelo.domain.common.usecase.DisposableUseCase

abstract class BaseViewModel(
    private val disposableUseCase: DisposableUseCase
) : ViewModel() {

    override fun onCleared() {
        disposableUseCase.dispose()
        super.onCleared()
    }
}
