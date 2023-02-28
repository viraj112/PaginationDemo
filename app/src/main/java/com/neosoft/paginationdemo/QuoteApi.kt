package com.neosoft.paginationdemo

import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/quotes")
   suspend fun getquotes(@Query("page") page:Int):QuoteList

    @GET("posts")
    suspend fun getPost() : List<Post>
}