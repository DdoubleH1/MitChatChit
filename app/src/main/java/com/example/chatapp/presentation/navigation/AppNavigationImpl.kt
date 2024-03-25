package com.example.chatapp.presentation.navigation

import android.os.Bundle
import com.example.chatapp.R
import com.example.chatapp.domain.core.navigation.BaseNavigatorImpl

class AppNavigationImpl : AppNavigation, BaseNavigatorImpl() {
    override fun openSplashToLoginScreen(bundle: Bundle?) {
        openScreen(R.id.action_splashFragment_to_loginFragment)
    }
}