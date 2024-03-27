package com.example.chatapp.presentation.screen.authentication

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegisterBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: RegisterViewModel by viewModels()
    override fun getVM() = viewModel

    override fun setOnClick() {
        super.setOnClick()
        binding.icBack.setOnClickListener {
            appNavigation.openRegisterToLoginScreen()
        }
    }


}