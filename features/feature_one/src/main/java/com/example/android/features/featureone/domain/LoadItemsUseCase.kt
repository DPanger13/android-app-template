package com.example.android.features.featureone.domain

import com.example.android.features.featureone.data.ItemsRepository
import com.example.android.features.featureone.viewmodel.ItemsUiState
import com.example.android.features.featureone.viewmodel.UiItem
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

internal class LoadItemsUseCase
    @Inject
    constructor(
        private val repository: ItemsRepository,
    ) {
        suspend operator fun invoke(): ItemsUiState =
            when (val result = repository.all()) {
                null -> ItemsUiState.Error
                else -> {
                    val items =
                        result
                            .map { UiItem(name = it.name) }
                            .toImmutableList()
                    ItemsUiState.Success(items)
                }
            }
    }
