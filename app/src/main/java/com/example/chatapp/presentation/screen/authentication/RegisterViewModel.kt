package com.example.chatapp.presentation.screen.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chatapp.domain.core.base.BaseViewModel
import com.example.chatapp.domain.repository.AuthRepository
import com.example.chatapp.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {
    private var _signUpResponse = MutableLiveData<Response<Boolean>>()
    val signUpResponse: MutableLiveData<Response<Boolean>> get() = _signUpResponse

    private var _validator = MutableLiveData<Boolean>()
    val validator: MutableLiveData<Boolean> get() = _validator


    fun signUp(email: String, userName: String, password: String) {
        viewModelScope.launch {
            signUpResponse.value = Response.Loading
            signUpResponse.value = authRepository.signUp(email, userName, password)
        }
    }

    fun setValidState(
        isValidEmail: Boolean = false,
        isValidPassword: Boolean = false,
        isValidUserName: Boolean = false,
        isCheckAgree: Boolean = false
    ) {
        _validator.value = isValidEmail && isValidPassword && isValidUserName && isCheckAgree
    }

}