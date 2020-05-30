package com.hardiktrivedi.theinternationaldhaba

import android.app.Application
import com.hardiktrivedi.theinternationaldhaba.di.networkModule
import com.hardiktrivedi.theinternationaldhaba.di.repositoryModule
import com.hardiktrivedi.theinternationaldhaba.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Overriding android Application class.
 */
class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin()
    }

    /**
     * A function to initialize and set up dependency injection framework named Koin.
     * The context of Android applicartion and various modules has been added.
     */
    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@RecipeApplication)
            modules(listOf(repositoryModule, viewModelModule, networkModule))
        }
    }
}
