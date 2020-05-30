package com.hardiktrivedi.theinternationaldhaba.recipeDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * A subclass of [ViewStateAwareViewModel] which gets the details of [Recipe] and display
 * It also display loader until the data is loaded.
 *
 * @param repository Instance of [SearchRecipeRepository]
 */
class RecipeDetailViewModel(private val repository: SearchRecipeRepository) :
    ViewStateAwareViewModel() {
    /**
     * Performs an API call using repository
     */
    fun fetchRecipeById(recipe: Recipe): LiveData<Recipe> {
        return repository.searchRecipeById(recipeId = recipe.id)
            .onStart {
                _progress.postValue(true)
            }
            .catch { e -> handleError(e) }
            .onCompletion { _progress.postValue(false) }
            .asLiveData()
    }
}
