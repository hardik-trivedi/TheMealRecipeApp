package com.hardiktrivedi.theinternationaldhaba.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import com.hardiktrivedi.theinternationaldhaba.repository.data.mapToRecipe
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareViewModel
import kotlinx.coroutines.flow.*

/**
 * A subclass of [ViewStateAwareViewModel] which gets the list of [Recipe] and display.
 * It also display loader until the data is loaded.
 *
 * @param repository Instance of [SearchRecipeRepository]
 */
class RecipeListViewModel(private val repository: SearchRecipeRepository) :
    ViewStateAwareViewModel() {

    /**
     * Fetches recipe by it's name.
     *
     * @param recipeName Name of selected recipe.
     * @return LiveData<List<Recipe>> emits list of [Recipe]
     */
    fun getRecipeByName(recipeName: String): LiveData<List<Recipe>> {
        return repository.searchRecipeByName(recipeName)
                .onStart {
                    _progress.postValue(true)
                }
                .catch { e -> handleError(e) }
                .onCompletion { _progress.postValue(false) }
                .asLiveData()

    }
}
