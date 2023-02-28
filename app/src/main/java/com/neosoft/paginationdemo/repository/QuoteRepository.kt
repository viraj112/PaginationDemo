package com.neosoft.paginationdemo.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.neosoft.paginationdemo.QuoteApi
import com.neosoft.paginationdemo.QuoteDatabase
import com.neosoft.paginationdemo.model.Post
import com.neosoft.paginationdemo.paging.QuotePagingSource
import com.neosoft.paginationdemo.paging.QuoteRemoteMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalPagingApi
class QuoteRepository @Inject constructor(
    private val quotesAPI: QuoteApi,
    private val quoteDatabase: QuoteDatabase
) {

    fun getQuotes() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = QuoteRemoteMediator(quotesAPI, quoteDatabase),
        pagingSourceFactory = { quoteDatabase.quoteDao().getQuotes() }
    ).liveData


    fun getPost() : Flow<List<Post>> = flow {
        val postList= quotesAPI.getPost()
        emit(postList)
    }.flowOn(Dispatchers.IO)
}