package com.neosoft.paginationdemo.flow

import com.neosoft.paginationdemo.QuoteApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Url

object RetrofitBuilder {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api:QuoteApi by lazy {
        retrofit.create(QuoteApi::class.java)
    }
}