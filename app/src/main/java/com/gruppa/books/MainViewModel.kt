package com.gruppa.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){


    private val _countLiveData: MutableLiveData<Int> = MutableLiveData(0)
    val countLiveData: LiveData<Int> = _countLiveData

    fun inc(){
        val count = _countLiveData.value ?: 0
        _countLiveData.value = count + 1
    }


}