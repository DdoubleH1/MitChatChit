package com.example.chatapp.utils

sealed class Response<out T> {
    data object Loading : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val e: Exception) : Response<Nothing>()
}