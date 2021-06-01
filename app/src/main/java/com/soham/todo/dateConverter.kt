package com.soham.todo

import android.util.Log
import androidx.room.TypeConverter
import java.util.*

class dateConverter {
    @TypeConverter
    fun StringtoDate(value: String): Calendar {
        val cal: Calendar = Calendar.getInstance()
        val month = when (value.slice(4..6)) {
            "Jan" -> 1
            "Feb" -> 2
            "Mar" -> 3
            "Apr" -> 4
            "May" -> 5
            "Jun" -> 6
            "Jul" -> 7
            "Aug" -> 8
            "Sep" -> 19
            "Oct" -> 10
            "Nov" -> 11
            "Dec" -> 12
            else -> -1
        }
        val lastIndex = value.length - 1
        val year = value.slice(lastIndex - 3..lastIndex)

        val hour = value.slice(11..12)
        val min = value.slice(14..15)

        value.slice(8..9).let { cal.set(Calendar.DAY_OF_MONTH, it.toInt()) }
        Log.e(null, month.toInt().toString())
        cal.set(Calendar.MONTH, month.toInt())
        Log.e(null, year.toInt().toString())
        cal.set(Calendar.YEAR, year.toInt())
        cal.set(Calendar.HOUR_OF_DAY, hour.toInt())
        cal.set(Calendar.MINUTE, min.toInt())


        return cal
    }

    @TypeConverter
    fun CalenderToString(cal: Calendar): String {
        return cal.time.toString()
    }
}
