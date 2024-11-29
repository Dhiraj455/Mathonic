package com.example.mathonic.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {
    @TypeConverter
    fun fromArrayToString(numbers: Array<Int>): String {
        return Gson().toJson(numbers)
    }

    @TypeConverter
    fun fromStringToArray(numbers: String): Array<Int> {
        val listType = object : TypeToken<Array<Int>>() {}.type
        return Gson().fromJson(numbers, listType)
    }
}
