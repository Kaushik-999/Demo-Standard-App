package com.kaushik.demostandardapp.utils

import com.kaushik.demostandardapp.network.models.Post

sealed class ApiState {
    object Empty: ApiState()
    object Loading: ApiState()
    data class Success(val data: Post): ApiState()
    data class Error(val errorMessage: Throwable): ApiState()
}