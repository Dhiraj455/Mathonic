package com.example.mathonic

import android.app.Application
import androidx.room.Room
import com.example.mathonic.db.MathonicDatabase

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