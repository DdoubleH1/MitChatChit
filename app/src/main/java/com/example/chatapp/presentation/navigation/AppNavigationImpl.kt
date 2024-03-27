package com.example.chatapp.presentation.navigation

import android.os.Bundle
import com.example.chatapp.R
import com.example.chatapp.domain.core.navigation.BaseNavigatorImpl
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppNavigationImpl @Inject constructor() : AppNavigation, BaseNavigatorImpl() {
    override fun openSplashToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_splashFragment_to_loginFragment)
    }

    override fun openLoginToRegisterScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_registerFragment)
    }

    override fun openRegisterToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_registerFragment_to_loginFragment)
    }
}