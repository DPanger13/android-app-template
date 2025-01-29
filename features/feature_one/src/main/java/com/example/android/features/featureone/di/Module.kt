package com.example.android.features.featureone.di

import com.example.android.features.featureone.data.ItemsRepository
import com.example.android.features.featureone.data.ItemsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal class Module {
    @Provides
    fun provideItemRepository(): ItemsRepository = ItemsRepositoryImpl(Dispatchers.Default)
}
