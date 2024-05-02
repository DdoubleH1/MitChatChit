package com.example.chatapp.presentation.screen.authentication

import android.os.Bundle
import androidx.fragment.app.viewModels
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

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        Spanner.spanString(binding.tvLogin, resources.getString(R.string.tv_login_now), resources){
            appNavigation.openRegisterToLoginScreen()
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.icBack.setOnClickListener {
            appNavigation.openRegisterToLoginScreen()
        }
    }


}