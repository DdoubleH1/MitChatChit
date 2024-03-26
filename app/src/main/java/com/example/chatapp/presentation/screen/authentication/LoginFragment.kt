package com.example.chatapp.presentation.screen.authentication

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.domain.core.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()
    override fun getVM() = viewModel


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        spanRegisterString()
    }

    private fun spanRegisterString() {
        val registerNowString = resources.getString(R.string.tv_register_now)
        val registerString = binding.tvRegister.text.toString()
        val spannableStringBuilder = SpannableStringBuilder(registerString)
        spannableStringBuilder.apply {
            setSpan(
                ForegroundColorSpan(
                    resources.getColor(
                        R.color.tv_login,
                        null
                    )
                ),
                registerString.indexOf(registerNowString),
                registerString.indexOf(registerNowString) + registerNowString.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            setSpan(
                StyleSpan(Typeface.BOLD),
                registerString.indexOf(registerNowString),
                registerString.indexOf(registerNowString) + registerNowString.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
        binding.tvRegister.text = spannableStringBuilder
    }


}