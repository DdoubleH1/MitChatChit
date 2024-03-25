package com.example.chatapp.presentation.navigation

import android.os.Bundle
import com.example.chatapp.domain.core.navigation.BaseNavigator

interface AppNavigation: BaseNavigator {
    fun openSplashToLoginScreen(bundle: Bundle? = null)
}