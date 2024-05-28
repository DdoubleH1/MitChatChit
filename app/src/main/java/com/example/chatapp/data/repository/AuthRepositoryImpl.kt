package com.example.chatapp.data.repository

import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@ViewModelScoped
class AuthRepositoryImpl @Inject constructor() : AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val database = Firebase.database

    override suspend fun signUp(
        email: String,
        userName: String,
        password: String
    ): Response<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            database.reference.child("users")
                .child(auth.currentUser!!.uid)
                .child("profile")
                .apply {
                    child("email").setValue(email)
                    child("display_name").setValue(userName)
                }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun login(email: String, password: String): Response<Boolean> {
        return try{
            auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(true)
        }
        catch (e: Exception) {
            Response.Error(e)
        }
    }

    override suspend fun logOut(): Response<Boolean> {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    override fun isLogin(): Boolean {
        return auth.currentUser != null
    }
}