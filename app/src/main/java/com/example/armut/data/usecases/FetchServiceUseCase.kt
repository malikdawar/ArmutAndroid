package com.example.armut.data.usecases

import com.example.armut.data.repository.service.ServiceRepository
import javax.inject.Inject

/**
 * A use-case to load the Movies from API.
 * @author Malik Dawar
 */
class FetchServiceUseCase @Inject constructor(private val repository: ServiceRepository) {
    suspend operator fun invoke(serviceId: String) = repository.fetchServiceById(serviceId)
}