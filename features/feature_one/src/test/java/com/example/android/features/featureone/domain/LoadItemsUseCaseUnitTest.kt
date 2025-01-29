package com.example.android.features.featureone.domain

import com.example.android.features.featureone.data.Item
import com.example.android.features.featureone.data.ItemsRepository
import com.example.android.features.featureone.viewmodel.ItemsUiState
import com.example.android.features.featureone.viewmodel.UiItem
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LoadItemsUseCaseUnitTest {
    private val repository: ItemsRepository = mockk()
    private lateinit var useCase: LoadItemsUseCase

    @BeforeEach
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)

        useCase = LoadItemsUseCase(repository)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenErrorLoadingData_thenErrorReturned() =
        runTest {
            coEvery { repository.all() } returns null

            useCase.invoke() shouldBe ItemsUiState.Error
        }

    @Test
    fun whenSuccessfullyLoadedData_thenSuccessReturned() =
        runTest {
            val item = Item(name = "Cadillac")
            coEvery { repository.all() } returns listOf(item)

            val uiItem = UiItem(name = item.name)
            useCase.invoke() shouldBe ItemsUiState.Success(listOf(uiItem).toImmutableList())
        }
}
