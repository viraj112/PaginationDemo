package com.neosoft.paginationdemo.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neosoft.paginationdemo.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class RoomDatabaseActivity : AppCompatActivity() {
    lateinit var database: ContactDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_database)

        database = ContactDatabase.getDatabase(this)

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0,"Viraj","9999", Date(),1))
        }
    }

    fun getData(view: View) {
        database.contactDao().getContact().observe(this) {
            Log.d("DEMO", "getData: ${it.toString()}")
        }
    }
}