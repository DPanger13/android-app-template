package com.example.android.features.featureone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.features.featureone.domain.LoadItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel
    @Inject
    internal constructor(
        private val loadItems: LoadItemsUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<ItemsUiState>(ItemsUiState.Loading)
        val uiState: StateFlow<ItemsUiState> = _uiState.asStateFlow()

        init {
            viewModelScope.launch {
                _uiState.value = loadItems()
            }
        }
    }
