package com.example.mathonic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MathonicViewModel  :ViewModel() {
    private val mathonicDao = MainApplication.mathonicDatabase.getMathonicDao()
    val levelList : LiveData<List<Levels>> = mathonicDao.getAllLevels()

    fun addLevel(number: Array<Int>, result: Int){
        viewModelScope.launch(Dispatchers.IO) {
            mathonicDao.addLevels(Levels(numbers = number, result = result))
        }
    }

    fun clearLevel() {
        viewModelScope.launch(Dispatchers.IO) {
            mathonicDao.clearLevels()
        }
    }
}