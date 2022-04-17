package com.example.armut.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.armut.data.DataState
import com.example.armut.data.model.HomeModel
import com.example.armut.data.model.Post
import com.example.armut.data.model.Service
import com.example.armut.data.usecases.FetchHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The HomeViewModel.kt, viewModel to perform the actions of the home screens and to manipulate the fetched data
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchHomeUseCase: FetchHomeUseCase
) : ViewModel() {


}
