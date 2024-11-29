package com.example.mathonic

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Levels(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val numbers: Array<Int>,
    val result: Int,
    val isCompleted : Boolean = false
)
