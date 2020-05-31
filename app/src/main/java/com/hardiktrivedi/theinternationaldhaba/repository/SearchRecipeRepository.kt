package com.hardiktrivedi.theinternationaldhaba.repository

import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import io.reactivex.rxjava3.core.Single

/**
 * A contract for Recipe repository. It holds list of all available function which a class needs to implement.
 */
interface SearchRecipeRepository {
    /**
     * Fetches recipe by it's name
     *
     * @param recipeName Name of recipe
     * @return Single<List<RecipeBasicInfo>> list of five Recipe as [Single]
     */
    fun searchRecipeByName(recipeName: String): Single<List<Recipe>>

    /**
     * Fetches recipe by it's identifier
     *
     * @param recipeId identifier of recipe e.g. 12345
     * @return Single<List<RecipeBasicInfo>> list of five Recipe as [Single]
     */
    fun searchRecipeById(recipeId: String): Single<Recipe>
}
