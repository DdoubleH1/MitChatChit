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
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {
    private var _signInResponse = MutableLiveData<Response<Boolean>>()
    val signInResponse: MutableLiveData<Response<Boolean>> get() = _signInResponse

    private var _validator = MutableLiveData(false)
    val validator: MutableLiveData<Boolean> get() = _validator
    private var _isEmailValid = false
    private var _isPasswordValid = false


    fun login(email: String, password: String) {
        viewModelScope.launch {
            signInResponse.value = Response.Loading
            signInResponse.value = authRepository.login(email, password)
        }
    }

    fun setValidState(isEmailValid: Boolean? = _isEmailValid, isPasswordValid: Boolean? = _isPasswordValid) {
        _isEmailValid = isEmailValid!!
        _isPasswordValid = isPasswordValid!!
        _validator.value = (_isEmailValid && _isPasswordValid)
    }

    val isLoginValid: Boolean
        get() = authRepository.isLogin()

}