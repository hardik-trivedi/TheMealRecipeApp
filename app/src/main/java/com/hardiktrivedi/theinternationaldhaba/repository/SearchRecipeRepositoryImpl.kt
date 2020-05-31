package com.hardiktrivedi.theinternationaldhaba.repository

import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.NoRecipeOfTheDayFoundException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import com.hardiktrivedi.theinternationaldhaba.repository.data.mapToRecipe
import com.hardiktrivedi.theinternationaldhaba.repository.network.SearchRecipeService
import io.reactivex.rxjava3.core.Single
import java.io.IOException
import java.util.concurrent.TimeUnit

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

    override fun searchRecipeByName(recipeName: String): Single<List<Recipe>> {
        return searchRecipeService.getRecipeByName(recipeName)
            .delay(delay, TimeUnit.MILLISECONDS)
            .map { it.meals.map { it.mapToRecipe() } }
            .doOnError { e ->
                transformError(e)
            }
    }

    override fun searchRecipeById(recipeId: String): Single<Recipe> {
        return searchRecipeService.getRecipeById(recipeId)
            .delay(delay, TimeUnit.MILLISECONDS)
            .map { it.meals }
            .map { it.first() }
            .map { it.mapToRecipe() }
            .doOnError { e ->
                transformError(e)
            }
    }

    private fun transformError(e: Throwable): Nothing {
        throw when (e) {
            is NoSuchElementException -> NoRecipeOfTheDayFoundException()
            is IOException -> NoInternetAvailableException()
            else -> SomethingWentWrongException()
        }
    }
}
