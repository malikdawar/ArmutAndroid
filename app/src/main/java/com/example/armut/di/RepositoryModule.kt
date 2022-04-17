package com.example.armut.di

import com.example.armut.data.remote.apiservice.ArmutApiInterface
import com.example.armut.data.repository.movies.HomeRepository
import com.example.armut.data.repository.movies.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author Malik Dawar
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMoviesRepository(armutApiService: ArmutApiInterface): HomeRepository {
        return HomeRepositoryImpl(armutApiService)
    }
}
