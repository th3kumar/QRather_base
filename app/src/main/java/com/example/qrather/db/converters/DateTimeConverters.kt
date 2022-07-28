package com.example.qrather.db.converters

import androidx.room.TypeConverter
import java.util.*

class DateTimeConverters {

    @TypeConverter
    fun toCalendar(l : Long) : Calendar? {
        val cl = Calendar.getInstance()
        cl!!.timeInMillis = l
        return cl
    }

    @TypeConverter
    fun fromCalendar(cl : Calendar?) : Long? {
        return cl?.time?.time
    }
}