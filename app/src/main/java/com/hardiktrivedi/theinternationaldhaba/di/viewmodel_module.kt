package com.hardiktrivedi.theinternationaldhaba.di

import com.hardiktrivedi.theinternationaldhaba.recipeDetail.RecipeDetailViewModel
import com.hardiktrivedi.theinternationaldhaba.recipeList.RecipeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * A property of type module. Used to initialize all view model class and it's dependency
 */
val viewModelModule = module {
    // View model module
    viewModel { RecipeListViewModel(get()) }
    viewModel { RecipeDetailViewModel(get()) }
}
