package com.example.armut.data.repository.home

import androidx.annotation.WorkerThread
import com.example.armut.core.extensions.noNetworkErrorMessage
import com.example.armut.core.extensions.somethingWentWrong
import com.example.armut.data.DataState
import com.example.armut.data.model.HomeModel
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
 * This is an implementation of [HomeRepository] to handle communication with [ArmutApiInterface] server.
 * @author Malik Dawar
 */
class HomeRepositoryImpl @Inject constructor(
    private val armutApiService: ArmutApiInterface
) : HomeRepository {

    @WorkerThread
    override suspend fun fetchHomeUiItems():
            Flow<DataState<HomeModel>> {
        return flow {
            armutApiService.fetchHomeUiItems().apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(DataState.error<HomeModel>(message()))
                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                if (this.exception is IOException) {
                    emit(DataState.error<HomeModel>(noNetworkErrorMessage()))
                } else {
                    emit(DataState.error<HomeModel>(somethingWentWrong()))
                }
            }
        }
    }
}