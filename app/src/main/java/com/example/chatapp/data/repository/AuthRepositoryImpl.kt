package com.example.chatapp.data.repository

import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.utils.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl : AuthRepository {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    override suspend fun signUp(
        email: String,
        userName: String,
        password: String
    ): Response<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = auth.currentUser?.uid
                    val user = hashMapOf(
                        "uid" to uid,
                        "userName" to userName,
                        "email" to email
                    )
                    database.reference.child("users").child(uid!!).child("profile").setValue(user)
                }
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