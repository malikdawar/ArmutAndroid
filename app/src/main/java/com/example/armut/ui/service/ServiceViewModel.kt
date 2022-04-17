package com.example.armut.ui.service

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armut.data.DataState
import com.example.armut.data.model.HomeModel
import com.example.armut.data.model.Service
import com.example.armut.data.usecases.FetchServiceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ServiceViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val fetchServiceUseCase: FetchServiceUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<ServiceUiState>()
    var uiStateLiveData: LiveData<ServiceUiState> = _uiState

    private var _servicesLiveData = MutableLiveData<Service>()
    var servicesLiveData: MutableLiveData<Service> = _servicesLiveData


    @SuppressLint("NullSafeMutableLiveData")
    fun fetchServiceById(serviceId: Int) {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchServiceUseCase.invoke(serviceId.toString()).collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        _servicesLiveData.value = dataState.data
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }
}
