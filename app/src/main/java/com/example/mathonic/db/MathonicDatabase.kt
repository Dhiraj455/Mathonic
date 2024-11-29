package com.example.mathonic.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mathonic.Levels

@Database(entities = [Levels::class], version = 1)
@TypeConverters(Converter::class)
abstract class MathonicDatabase : RoomDatabase() {
    companion object {
        const val NAME = "Mathonic_DB"
    }

    abstract fun getMathonicDao() : MathonicDao

}