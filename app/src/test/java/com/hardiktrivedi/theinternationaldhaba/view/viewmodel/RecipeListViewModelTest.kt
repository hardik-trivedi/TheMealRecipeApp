package com.hardiktrivedi.theinternationaldhaba.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.hardiktrivedi.theinternationaldhaba.InstantExecutorExtension
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.latestRecipesResponse
import com.hardiktrivedi.theinternationaldhaba.recipeList.RecipeListViewModel
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
internal class RecipeListViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: SearchRecipeRepository

    lateinit var viewModel: RecipeListViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel =
            RecipeListViewModel(repository)
    }

    @AfterEach
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Nested
    @DisplayName("Given user is on recipe list screen")
    inner class RecipeList {

        @MockK
        lateinit var loaderObserver: Observer<Boolean>

        @BeforeEach
        fun setup() {
            MockKAnnotations.init(this, relaxUnitFun = true)
            every { loaderObserver.onChanged(any()) } just runs
        }

        @Nested
        @DisplayName("When internet is connected")
        inner class WhenInternetIsConnected {
            @MockK
            lateinit var responseObserver: Observer<List<Recipe>>

            @BeforeEach
            fun setup() {
                MockKAnnotations.init(this, relaxUnitFun = true)
                every { responseObserver.onChanged(any()) } just runs
            }

            @Test
            @DisplayName("Then recipe by name successfully retrieved with correct loaders")
            fun `retrieves recipe by name`() {
                coEvery { repository.searchRecipeByName(any()) } returns flowOf(
                    latestRecipesResponse
                )

                testDispatcher.runBlockingTest {
                    viewModel.run {
                        progress.observeForever(loaderObserver)
                        getRecipeByName("Paneer").observeForever(responseObserver)
                        progress.observeForever(loaderObserver)
                    }

                    verifyOrder {
                        loaderObserver.onChanged(true)
                        responseObserver.onChanged(latestRecipesResponse)
                        loaderObserver.onChanged(false)
                    }
                }
            }
        }

        @Nested
        @DisplayName("When something went wrong")
        inner class WhenSomethingWentWrong {

            @MockK
            lateinit var responseObserver: Observer<List<Recipe>>

            @MockK
            lateinit var exceptionObserver: Observer<SomethingWentWrongException>

            val exception = SomethingWentWrongException()

            @BeforeEach
            fun setup() {
                MockKAnnotations.init(this, relaxUnitFun = true)
                every { exceptionObserver.onChanged(any()) } just runs
            }

            @Test
            @DisplayName("Then recipe by name throws SomethingWentWrongException")
            fun `recipe by name throws SomethingWentWrongException`() {

                coEvery { repository.searchRecipeByName(any()) } returns flow { throw exception }

                testDispatcher.runBlockingTest {
                    viewModel.run {
                        somethingWentWrongException.observeForever(exceptionObserver)
                        getRecipeByName("Paneer").observeForever(responseObserver)
                    }

                    verify { exceptionObserver.onChanged(exception) }
                    verify(exactly = 0) { responseObserver.onChanged(any()) }
                }
            }
        }

        @Nested
        @DisplayName("When internet is not connected")
        inner class WhenInternetIsNotConnected {

            @MockK
            lateinit var responseObserver: Observer<List<Recipe>>

            @MockK
            lateinit var exceptionObserver: Observer<NoInternetAvailableException>

            val exception = NoInternetAvailableException()

            @BeforeEach
            fun setup() {
                MockKAnnotations.init(this, relaxUnitFun = true)
                every { exceptionObserver.onChanged(any()) } just runs
            }

            @Test
            @DisplayName("Then recipe by name throws NoInternetAvailableException")
            fun `recipe by name throws NoInternetAvailableException`() {

                coEvery { repository.searchRecipeByName(any()) } returns flow { throw exception }

                testDispatcher.runBlockingTest {
                    viewModel.run {
                        networkException.observeForever(exceptionObserver)
                        getRecipeByName("Paneer").observeForever(responseObserver)
                    }

                    verify { exceptionObserver.onChanged(exception) }
                    verify(exactly = 0) { responseObserver.onChanged(any()) }
                }
            }
        }
    }
}