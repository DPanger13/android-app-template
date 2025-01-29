package com.example.android.features.featureone.viewmodel

import com.example.android.features.featureone.domain.LoadItemsUseCase
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

class ExampleViewModelUnitTest {
    private val useCase: LoadItemsUseCase = mockk()
    private lateinit var viewModel: ExampleViewModel

    @BeforeEach
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun whenErrorLoadingData_thenUiStateIsError() =
        runTest {
            coEvery { useCase.invoke() } returns ItemsUiState.Error

            viewModel = ExampleViewModel(useCase)

            viewModel.uiState.value shouldBe ItemsUiState.Error
        }

    @Test
    fun whenSuccessfullyLoadedData_thenUiStateIsSuccess() =
        runTest {
            val items = emptyList<UiItem>().toImmutableList()
            coEvery { useCase.invoke() } returns ItemsUiState.Success(items)

            viewModel = ExampleViewModel(useCase)

            viewModel.uiState.value shouldBe ItemsUiState.Success(items)
        }
}
