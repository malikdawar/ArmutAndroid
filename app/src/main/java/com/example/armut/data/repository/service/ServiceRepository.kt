package com.example.armut.data.repository.service

import com.example.armut.data.DataState
import com.example.armut.data.model.Service
import kotlinx.coroutines.flow.Flow

/**
 * ServiceRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [ServiceRepositoryImpl] for implementation of this class to utilize Armut API.
 * @author Malik Dawar
 */
interface ServiceRepository {
    suspend fun fetchServiceById(serviceId: String): Flow<DataState<Service>>
}