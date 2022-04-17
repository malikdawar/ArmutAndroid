package com.example.armut.data.repository.service

import androidx.annotation.WorkerThread
import com.example.armut.core.extensions.noNetworkErrorMessage
import com.example.armut.core.extensions.somethingWentWrong
import com.example.armut.data.DataState
import com.example.armut.data.model.HomeModel
import com.example.armut.data.model.Service
import com.example.armut.data.remote.apiservice.ArmutApiInterface
import com.example.armut.data.remote.message
import com.example.armut.data.remote.onErrorSuspend
import com.example.armut.data.remote.onExceptionSuspend
import com.example.armut.data.remote.onSuccessSuspend
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * This is an implementation of [ServiceRepository] to handle communication with [ArmutApiInterface], fetchServiceById().
 * @author Malik Dawar
 */
class ServiceRepositoryImpl @Inject constructor(
    private val armutApiService: ArmutApiInterface
) : ServiceRepository {

    @WorkerThread
    override suspend fun fetchServiceById(serviceId: String):
            Flow<DataState<Service>> {
        return flow {
            armutApiService.fetchServiceById(serviceId = serviceId).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<Service>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<Service>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<Service>(somethingWentWrong()))
                }
            }
        }
    }
}