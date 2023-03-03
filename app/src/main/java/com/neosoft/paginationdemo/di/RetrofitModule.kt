package com.neosoft.paginationdemo.di

import com.neosoft.paginationdemo.QuoteApi
import com.neosoft.paginationdemo.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    @Singleton
    @Provides
    fun getQuoteAPI(retrofit: Retrofit): QuoteApi{
        return retrofit.create(QuoteApi::class.java)
    }
}