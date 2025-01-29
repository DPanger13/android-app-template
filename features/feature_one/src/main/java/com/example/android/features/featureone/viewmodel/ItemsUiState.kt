package com.example.android.features.featureone.viewmodel

import kotlinx.collections.immutable.ImmutableList

sealed interface ItemsUiState {
    data object Loading : ItemsUiState

    data class Success(val items: ImmutableList<UiItem>) : ItemsUiState

    data object Error : ItemsUiState
}
