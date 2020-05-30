package com.hardiktrivedi.theinternationaldhaba.repository

import com.hardiktrivedi.theinternationaldhaba.*
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.NoRecipeOfTheDayFoundException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.repository.network.SearchRecipeService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.jupiter.api.*

internal class SearchRecipeRepositoryImplTest {
    private val delay = 2_000L

    @MockK
    lateinit var searchRecipeService: SearchRecipeService

    lateinit var repository: SearchRecipeRepositoryImpl

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = SearchRecipeRepositoryImpl(searchRecipeService)
    }

    @Nested
    @DisplayName("Given no network is available")
    inner class NoNetworkAvailable {

        @Test
        @DisplayName("when trying to fetch recipe by name, then NoInternetAvailableException is thrown ")
        fun `fetch recipe by name throws NoInternetAvailableException`() {
            coEvery { searchRecipeService.getRecipeByName(any()) } throws NoInternetAvailableException()
            val exception = assertThrows<Exception> {
                runBlockingTest {
                    advanceTimeBy(delay)
                    repository.searchRecipeByName("Potato").first()
                }
            }

            Assert.assertTrue(exception is NoInternetAvailableException)
        }
    }

    @Nested
    @DisplayName("Given recipe is search by id")
    inner class RecipeDetaiById {
        @BeforeEach
        fun setup() {
            coEvery { searchRecipeService.getRecipeById(any()) } returns recipeListResponse
        }

        @Nested
        @DisplayName("When network is connected")
        inner class WhenNetworkConnected() {
            @Test
            @DisplayName("Then recipe detail can be fetched successfully")
            fun `recipe detail can be fetched successfully`() {
                runBlockingTest {
                    advanceTimeBy(delay)
                    val recipe = repository.searchRecipeById("1").first()
                    Assert.assertEquals(recipe, com.hardiktrivedi.theinternationaldhaba.recipe)
                }
            }
        }
    }
}
