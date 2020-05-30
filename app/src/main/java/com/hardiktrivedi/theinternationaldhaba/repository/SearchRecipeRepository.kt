package com.hardiktrivedi.theinternationaldhaba.repository

import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import kotlinx.coroutines.flow.Flow

/**
 * A contract for Recipe repository. It holds list of all available function which a class needs to implement.
 */
interface SearchRecipeRepository {
    /**
     * Fetches recipe by it's name
     *
     * @param recipeName Name of recipe
     * @return Flow<List<RecipeBasicInfo>> list of five Recipe as [Flow]
     */
    fun searchRecipeByName(recipeName: String): Flow<List<Recipe>>

    /**
     * Fetches recipe by it's identifier
     *
     * @param recipeId identifier of recipe e.g. 12345
     * @return Flow<List<RecipeBasicInfo>> list of five Recipe as [Flow]
     */
    fun searchRecipeById(recipeId: String): Flow<Recipe>
}
