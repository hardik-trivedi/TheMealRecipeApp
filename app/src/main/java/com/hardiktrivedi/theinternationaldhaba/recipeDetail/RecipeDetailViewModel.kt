package com.hardiktrivedi.theinternationaldhaba.recipeDetail

import androidx.lifecycle.MutableLiveData
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.asDisposable
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareViewModel

/**
 * A subclass of [ViewStateAwareViewModel] which gets the details of [Recipe] and display
 * It also display loader until the data is loaded.
 *
 * @param repository Instance of [SearchRecipeRepository]
 */
class RecipeDetailViewModel(private val repository: SearchRecipeRepository) :
    ViewStateAwareViewModel() {
    val recipeDetail = MutableLiveData<Recipe>()

    /**
     * Performs an API call using repository
     */
    fun fetchRecipeById(recipe: Recipe) {
        repository.searchRecipeById(recipeId = recipe.id)
            .doOnSubscribe {
                _progress.postValue(true)
            }
            .doFinally { _progress.postValue(false) }
            .subscribe(
                { recipeDetail.postValue(it) },
                { e -> handleError(e) }
            )
            .asDisposable(compositeDisposable)
    }
}
