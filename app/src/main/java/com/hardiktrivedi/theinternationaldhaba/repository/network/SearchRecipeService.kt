package com.hardiktrivedi.theinternationaldhaba.repository.network

import com.hardiktrivedi.theinternationaldhaba.repository.data.RecipeByResponse
import com.hardiktrivedi.theinternationaldhaba.repository.data.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface
 */
interface SearchRecipeService {
    @GET("search.php")
    suspend fun getRecipeByName(@Query("s") recipeName: String): RecipeByResponse

    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") recipeId: String): RecipeListResponse
}
