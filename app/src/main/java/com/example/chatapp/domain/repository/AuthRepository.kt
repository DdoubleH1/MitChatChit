package com.example.chatapp.domain.repository

import com.example.chatapp.utils.Response

interface AuthRepository {
    suspend fun signUp(email: String, userName: String, password: String): Response<Boolean>
    suspend fun login(email: String, password: String): Response<Boolean>
    suspend fun logOut(): Response<Boolean>
    fun isLogin(): Boolean
}