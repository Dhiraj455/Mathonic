package com.example.mathonic.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mathonic.Levels

@Dao
interface MathonicDao {
    @Insert
    fun addLevels(levels: Levels)

    @Query("SELECT * FROM LEVELS")
    fun getAllLevels() : LiveData<List<Levels>>

    @Query("DELETE FROM LEVELS")
    fun clearLevels()

}