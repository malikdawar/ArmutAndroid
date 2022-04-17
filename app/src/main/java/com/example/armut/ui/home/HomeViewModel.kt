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

    private var _uiState = MutableLiveData<MovieUiState>()
    var uiStateLiveData: LiveData<MovieUiState> = _uiState

    private var _allServicesLiveData = MutableLiveData<List<Service>>()
    var allServicesLiveData: MutableLiveData<List<Service>> = _allServicesLiveData

    private var _popularServicesLiveData = MutableLiveData<List<Service>>()
    var popularServicesLiveData: MutableLiveData<List<Service>> = _popularServicesLiveData

    private var _blogPostsLiveData = MutableLiveData<List<Post>>()
    var blogPostsLiveData: MutableLiveData<List<Post>> = _blogPostsLiveData

    init {
        fetchMovies()
    }

    @SuppressLint("NullSafeMutableLiveData")
    private fun fetchMovies() {
        _uiState.postValue(LoadingState)

        viewModelScope.launch {
            fetchHomeUseCase.invoke().collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _uiState.postValue(ContentState)
                        getHomeDataFiltered(dataState.data)
                    }

                    is DataState.Error -> {
                        _uiState.postValue(ErrorState(dataState.message))
                    }
                }
            }
        }
    }

    private fun getHomeDataFiltered(homeModel: HomeModel) {
        homeModel.allServices?.let {
            allServicesLiveData.value = it
        }
        homeModel.popular?.let {
            popularServicesLiveData.value = it
        }

        homeModel.posts?.let {
            blogPostsLiveData.value = it
        }
    }
}
