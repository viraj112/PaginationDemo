package com.neosoft.paginationdemo.roomdatabase

import androidx.room.TypeConverter
import java.util.Date

class Conveters {

    @TypeConverter
    fun fromDateToLong(value:Date):Long{
        return  value.time
    }

    @TypeConverter
    fun fromLongToDate(value:Long):Date{
        return Date(value)
    }
}