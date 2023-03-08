package com.neosoft.paginationdemo.repository

import com.neosoft.paginationdemo.di.RetrofitModule
import com.neosoft.paginationdemo.flow.RetrofitBuilder
import com.neosoft.paginationdemo.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository {

    fun    getPost() : Flow<List<Post>> = flow {
        val postList=RetrofitBuilder.api.getPost()
        emit(postList)
    }.flowOn(Dispatchers.IO)
}