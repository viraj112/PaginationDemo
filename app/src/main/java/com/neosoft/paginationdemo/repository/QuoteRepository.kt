package com.neosoft.paginationdemo.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.neosoft.paginationdemo.QuoteApi
import com.neosoft.paginationdemo.QuoteDatabase
import com.neosoft.paginationdemo.paging.QuotePagingSource
import com.neosoft.paginationdemo.paging.QuoteRemoteMediator
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

}