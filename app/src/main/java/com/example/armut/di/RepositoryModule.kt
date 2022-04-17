package com.example.armut.di

import com.example.armut.data.remote.apiservice.ArmutApiInterface
import com.example.armut.data.repository.home.HomeRepository
import com.example.armut.data.repository.home.HomeRepositoryImpl
import com.example.armut.data.repository.service.ServiceRepository
import com.example.armut.data.repository.service.ServiceRepositoryImpl
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
    fun provideHomeRepository(armutApiService: ArmutApiInterface): HomeRepository {
        return HomeRepositoryImpl(armutApiService)
    }

    @Singleton
    @Provides
    fun provideServiceRepository(armutApiService: ArmutApiInterface): ServiceRepository {
        return ServiceRepositoryImpl(armutApiService)
    }
}
