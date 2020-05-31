package com.hardiktrivedi.theinternationaldhaba.repository

import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.NoRecipeOfTheDayFoundException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.repository.data.mapToRecipe
import com.hardiktrivedi.theinternationaldhaba.repository.network.SearchRecipeService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException

/**
 * Implementation for [SearchRecipeRepository]
 *
 * @param searchRecipeService Retrofit interface [SearchRecipeService]
 */
class SearchRecipeRepositoryImpl(private val searchRecipeService: SearchRecipeService) :
    SearchRecipeRepository {

    companion object {
        private const val delay = 2_000L
    }

    override fun searchRecipeByName(recipeName: String): Flow<List<Recipe>> {
        return flowOfRecipe { searchRecipeService.getRecipeByName(recipeName) }
            .onEach { delay(delay) }
            .catch { e -> transformError(e) }
            .map { it.meals.map { it.mapToRecipe() } }
    }

    override fun searchRecipeById(recipeId: String): Flow<Recipe> {
        return flowOfRecipe { searchRecipeService.getRecipeById(recipeId) }
            .onEach { delay(delay) }
            .map { it.meals }
            .map { it.first() }
            .catch { e -> transformError(e) }
            .map { it.mapToRecipe() }
    }

    private fun transformError(e: Throwable): Nothing {
        throw when (e) {
            is NoSuchElementException -> NoRecipeOfTheDayFoundException()
            is IOException -> NoInternetAvailableException()
            else -> SomethingWentWrongException()
        }
    }

    private fun <T> flowOfRecipe(f: suspend () -> T): Flow<T> = flow {
        emit(f.invoke())
    }
}
