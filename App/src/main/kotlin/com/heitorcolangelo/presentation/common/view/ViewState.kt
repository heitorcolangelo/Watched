package com.heitorcolangelo.presentation.common.view

sealed class ViewState(
    val loadingVisibility: Visibility = Visibility.Gone,
    val contentVisibility: Visibility = Visibility.Gone,
    val errorVisibility: Visibility = Visibility.Gone,
    val emptyVisibility: Visibility = Visibility.Gone
) {
    object Loading : ViewState(loadingVisibility = Visibility.Visible)
    object Content : ViewState(contentVisibility = Visibility.Visible)
    object Error : ViewState(errorVisibility = Visibility.Visible)
    object Empty : ViewState(emptyVisibility = Visibility.Visible)
}
