package com.example.chatapp.presentation.container

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding
import com.example.chatapp.domain.core.base.BaseActivity
import com.example.chatapp.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: MainViewModel by viewModels()
    override fun getVM() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        Log.e("HoangDH", "$navHostFragment")
        appNavigation.bind(navHostFragment.navController)
    }


    override val layoutId = R.layout.activity_main
}