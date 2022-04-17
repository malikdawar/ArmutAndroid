package com.example.armut.data.repository.home

import com.example.armut.data.DataState
import com.example.armut.data.model.HomeModel
import kotlinx.coroutines.flow.Flow

/**
 * HomeRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [HomeRepositoryImpl] for implementation of this class to utilize Armut API.
 * @author Malik Dawar
 */
interface HomeRepository {
    suspend fun fetchHomeUiItems(): Flow<DataState<HomeModel>>
}