package com.example.mathonic

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mathonic.db.MathonicDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainApplication : Application() {
    companion object {
        lateinit var mathonicDatabase: MathonicDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        mathonicDatabase = Room.databaseBuilder(
            applicationContext,
            MathonicDatabase::class.java,
            MathonicDatabase.NAME
        ).build()
    }
}