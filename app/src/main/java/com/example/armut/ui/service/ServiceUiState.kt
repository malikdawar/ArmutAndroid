package com.example.armut.ui.service

sealed class ServiceUiState

object LoadingState : ServiceUiState()
object ContentState : ServiceUiState()
class ErrorState(val message: String) : ServiceUiState()
