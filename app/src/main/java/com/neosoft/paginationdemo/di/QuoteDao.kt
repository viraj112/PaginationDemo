package com.neosoft.paginationdemo.di

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neosoft.paginationdemo.model.Result

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun getQuotes(): PagingSource<Int, Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<Result>)

    @Query("DELETE FROM quote")
    suspend fun deleteQuotes()

}