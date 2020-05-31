package com.hardiktrivedi.theinternationaldhaba.di

import android.content.Context
import com.hardiktrivedi.theinternationaldhaba.R
import com.hardiktrivedi.theinternationaldhaba.repository.network.SearchRecipeService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A property of type module. Used to initialize all network call related dependency
 */
val networkModule = module {
    factory { provideRetrofitClient(androidContext()) }
    factory { provideRetrofitService(get()) }
}

private fun provideRetrofitService(retrofit: Retrofit): SearchRecipeService {
    return retrofit.create(SearchRecipeService::class.java)
}

private fun provideRetrofitClient(context: Context): Retrofit {
    val okHttpClient = OkHttpClient.Builder().apply {
        // addInterceptor(ErrorInterceptor(context))
    }.build()

    return Retrofit.Builder().baseUrl(context.getString(R.string.baseUrl))
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
}
