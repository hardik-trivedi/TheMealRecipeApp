package com.hardiktrivedi.theinternationaldhaba.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hardiktrivedi.theinternationaldhaba.InstantExecutorExtension
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.recipeDetail.RecipeDetailViewModel
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantExecutorExtension::class)
internal class RecipeDetailViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: SearchRecipeRepository

    lateinit var viewModel: RecipeDetailViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel =
            RecipeDetailViewModel(repository)
    }

    @AfterEach
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Nested
    @DisplayName("Given user taps on recipe item")
    inner class RecipeDetail {

        @Nested
        @DisplayName("When internet is connected")
        inner class WhenInternetIsConnected {
            @MockK
            lateinit var recipe: Recipe

            @MockK
            lateinit var responseObserver: Observer<Recipe>

            @MockK
            lateinit var loaderObserver: Observer<Boolean>

            @BeforeEach
            fun setup() {
                MockKAnnotations.init(this, relaxUnitFun = true)

                every { recipe.id } returns "1"
                every { loaderObserver.onChanged(any()) } just runs
            }

            @Test
            @DisplayName("Then recipe details are successfully retrieved with correct loaders")
            fun `retrieves recipe details`() {
                coEvery { repository.searchRecipeById(any()) } returns flowOf(recipe)
                every { responseObserver.onChanged(any()) } just runs

                testDispatcher.runBlockingTest {
                    viewModel.run {
                        progress.observeForever(loaderObserver)
                        fetchRecipeById(recipe).observeForever(responseObserver)
                        progress.observeForever(loaderObserver)
                    }

                    verifyOrder {
                        loaderObserver.onChanged(true)
                        responseObserver.onChanged(recipe)
                        loaderObserver.onChanged(false)
                    }
                }
            }

            @Test
            @DisplayName("Then SomethingWentWrongException is thrown")
            fun `SomethingWentWrongException is thrown`() {
                val exception = SomethingWentWrongException()
                val exceptionObserver = mockk<Observer<SomethingWentWrongException>>()

                coEvery { repository.searchRecipeById(any()) } returns flow { throw exception }
                every { responseObserver.onChanged(any()) } just runs
                every { exceptionObserver.onChanged(any()) } just runs

                testDispatcher.runBlockingTest {
                    viewModel.run {
                        somethingWentWrongException.observeForever(exceptionObserver)
                        fetchRecipeById(recipe).observeForever(responseObserver)
                    }

                    verify { exceptionObserver.onChanged(exception) }
                    verify(exactly = 0) { responseObserver.onChanged(any()) }
                }
            }
        }

    }
}