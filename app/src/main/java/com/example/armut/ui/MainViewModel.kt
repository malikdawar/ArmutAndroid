package com.example.armut.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * The MainViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class MainViewModel : ViewModel() {

    private val _serviceId = MutableLiveData(-1)
    val serviceId: LiveData<Int> = _serviceId

    fun setServiceId(serviceId: Int) {
        _serviceId.value = serviceId
    }
}
