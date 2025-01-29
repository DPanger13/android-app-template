package com.example.android.features.featureone.data

import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class ItemsRepositoryImpl
    @Inject
    constructor(private val coroutineContext: CoroutineContext) : ItemsRepository {
        override suspend fun all(): List<Item>? =
            withContext(coroutineContext) {
                TODO()
            }
    }
