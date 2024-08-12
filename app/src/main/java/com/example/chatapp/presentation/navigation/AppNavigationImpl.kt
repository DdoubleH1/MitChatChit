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

    override fun openRegisterToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_registerFragment_to_homeFragment)
    }

    override fun openLoginToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_loginFragment_to_homeFragment)
    }

    override fun openHomeScreenToCreateGroupScreen(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_createMessageFragment)
    }

    override fun openCreateGroupScreenToHomeScreen(bundle: Bundle?) {
        openScreen(R.id.action_createMessageFragment_to_homeFragment)
    }

    override fun openHomeScreenToDetailMessageScreen(bundle: Bundle?) {
        openScreen(R.id.action_homeFragment_to_detailMessageFragment)
    }

}