package com.example.chatapp.presentation.screen.splash

import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSplashBinding
import com.example.chatapp.domain.core.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by viewModels()
    override fun getVM() = viewModel



}