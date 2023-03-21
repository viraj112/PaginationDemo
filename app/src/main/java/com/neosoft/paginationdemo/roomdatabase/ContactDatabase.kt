package com.neosoft.paginationdemo.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.impl.Migration_1_2

@Database(entities = [Contact::class], version = 2, exportSchema = false)
@TypeConverters(Conveters::class)
abstract class ContactDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO

    companion object {
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        @Volatile
        private var INSTANCE: ContactDatabase? = null
        fun getDatabase(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contactDB"
                    ).addMigrations(migration_1_2).build()
                }
            }
            return INSTANCE!!
        }
    }
}