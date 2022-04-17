package com.example.armut.data.usecases

import com.example.armut.data.repository.movies.HomeRepository
import javax.inject.Inject

/**
 * A use-case to load the Movies from API.
 * @author Malik Dawar
 */
class FetchHomeUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke() = repository.fetchHomeUiItems()
}