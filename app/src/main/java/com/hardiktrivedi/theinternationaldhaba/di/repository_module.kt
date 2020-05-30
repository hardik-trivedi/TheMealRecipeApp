package com.hardiktrivedi.theinternationaldhaba.di

import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepository
import com.hardiktrivedi.theinternationaldhaba.repository.SearchRecipeRepositoryImpl
import org.koin.dsl.module

/**
 * A property of type module. Used to initialize repository class dependency
 */
val repositoryModule = module {
    single<SearchRecipeRepository> { SearchRecipeRepositoryImpl(get()) }
}
