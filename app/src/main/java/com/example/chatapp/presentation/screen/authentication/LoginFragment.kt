package com.example.chatapp.presentation.screen.authentication

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(R.layout.fragment_login) {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: LoginViewModel by viewModels()
    override fun getVM() = viewModel


    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        spanRegisterString()
    }

    private fun spanRegisterString() {
        val registerNowString = resources.getString(R.string.tv_register_now)
        val registerString = binding.tvRegister.text.toString()
        val spannableString = SpannableString(registerString)
        spannableString.apply {
            setSpan(
                ForegroundColorSpan(
                    resources.getColor(
                        R.color.primary_color_light,
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
            setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        Log.e("HoangDH", "OnClick")
                        appNavigation.openLoginToRegisterScreen()
                    }
                    override fun updateDrawState(ds: TextPaint) {
                        super.updateDrawState(ds)
                        ds.isUnderlineText = false
                    }
                }, registerString.indexOf(registerNowString),
                registerString.indexOf(registerNowString) + registerNowString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        binding.tvRegister.apply {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
        }
    }


}