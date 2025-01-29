package com.example.android.features.featureone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.android.features.featureone.viewmodel.ExampleViewModel
import com.example.android.features.featureone.viewmodel.ItemsUiState
import com.example.android.ui.components.error.ErrorMessage
import com.example.android.ui.components.progress.ProgressIndicator

const val ROUTE_FEATURE_ONE = "feature_one"

@Composable
fun FeatureOneScreen(
    viewModel: ExampleViewModel,
    modifier: Modifier = Modifier,
) {
    val uiStateHolder = viewModel.uiState.collectAsStateWithLifecycle()
    when (val uiState = uiStateHolder.value) {
        is ItemsUiState.Loading -> {
            ProgressIndicator(
                modifier = modifier,
            )
        }
        is ItemsUiState.Success -> {
            ExampleList(
                modifier = modifier,
                items = uiState.items,
            )
        }
        is ItemsUiState.Error -> {
            ErrorMessage(
                modifier = modifier,
            )
        }
    }
}
