package com.heitorcolangelo.presentation.common.view

sealed class ViewState {
    object Loading : ViewState()
    object Content : ViewState()
    object Error : ViewState()
}