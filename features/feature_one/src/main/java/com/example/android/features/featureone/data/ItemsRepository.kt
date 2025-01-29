package com.example.android.features.featureone.data

interface ItemsRepository {
    /**
     * Returns items or null if an error occurred.
     */
    suspend fun all(): List<Item>?
}
