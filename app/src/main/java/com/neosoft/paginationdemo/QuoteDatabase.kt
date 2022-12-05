package com.neosoft.paginationdemo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neosoft.paginationdemo.di.QuoteDao
import com.neosoft.paginationdemo.di.RemoteKeysDao
import com.neosoft.paginationdemo.model.QuoteRemoteKeys
import com.neosoft.paginationdemo.model.Result

@Database(entities = [Result::class, QuoteRemoteKeys::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao() : QuoteDao
    abstract fun remoteKeysDao() : RemoteKeysDao
}