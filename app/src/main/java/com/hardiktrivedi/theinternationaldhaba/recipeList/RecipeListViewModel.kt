package com.hardiktrivedi.theinternationaldhaba.recipeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hardiktrivedi.theinternationaldhaba.data.Recipe
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import com.hardiktrivedi.theinternationaldhaba.utility.extenstions.asDisposable
import com.hardiktrivedi.theinternationaldhaba.viewstate.ViewStateAwareViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A subclass of [ViewStateAwareViewModel] which gets the list of [Recipe] and display.
 * It also display loader until the data is loaded.
 *
 * @param repository Instance of [SearchRecipeRepository]
 */
class RecipeListViewModel(private val repository: SearchRecipeRepository) :
    ViewStateAwareViewModel() {
    private val _getRecipeByName = MutableLiveData<List<Recipe>>()
    val getRecipeByName = _getRecipeByName as LiveData<List<Recipe>>

    /**
     * Fetches recipe by it's name.
     *
     * @param recipeName Name of selected recipe.
     */
    fun getRecipeByName(recipeName: String) {
        repository.searchRecipeByName(recipeName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _progress.postValue(true)
            }
            .doFinally { _progress.postValue(false) }
            .subscribe(
                { _getRecipeByName.postValue(it) },
                { e -> handleError(e) }
            )
            .asDisposable(compositeDisposable)
    }
}
