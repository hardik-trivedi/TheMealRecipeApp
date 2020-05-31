package com.hardiktrivedi.theinternationaldhaba.repository.network

import com.hardiktrivedi.theinternationaldhaba.repository.data.RecipeByResponse
import com.hardiktrivedi.theinternationaldhaba.repository.data.RecipeListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface
 */
interface SearchRecipeService {
    @GET("search.php")
    fun getRecipeByName(@Query("s") recipeName: String): Single<RecipeByResponse>

    @GET("lookup.php")
    fun getRecipeById(@Query("i") recipeId: String): Single<RecipeListResponse>
}
