package com.example.chatapp.presentation.screen.authentication

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentRegisterBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.presentation.navigation.AppNavigation
import com.example.chatapp.utils.Prefs
import com.example.chatapp.utils.Response
import com.example.chatapp.utils.validate
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<FragmentRegisterBinding, RegisterViewModel>(R.layout.fragment_register) {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: RegisterViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        Spanner.spanString(binding.tvLogin, resources.getString(R.string.tv_login_now), resources) {
            appNavigation.openRegisterToLoginScreen()
        }
    }

    override fun setOnClick() {
        super.setOnClick()
        binding.icBack.setOnClickListener {
            appNavigation.openRegisterToLoginScreen()
        }
        Timber.tag("HoangDH").e(binding.etEmail.text.toString().trim())

        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
                binding.etEmail.text.toString().trim(),
                binding.etUsername.text.toString().trim(),
                binding.etPassword.text.toString().trim()
            )
        }
    }

    override fun bindingStateView() {
        super.bindingStateView()
        binding.apply {
            etEmail.validate { email ->
                if (email.isEmpty()) {
                    etEmail.error = resources.getString(R.string.do_not_leave_this_field_blank)
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.error = resources.getString(R.string.invalid_email)
                } else {
                    viewModel.setValidState(isValidEmail = true)
                }
            }
            etUsername.validate { userName ->
                if (userName.isEmpty()) {
                    etUsername.error = resources.getString(R.string.do_not_leave_this_field_blank)
                } else {
                    viewModel.setValidState(isValidUserName = true)
                }
            }
            etPassword.validate { password ->
                if (password.isEmpty()) {
                    etPassword.error = resources.getString(R.string.do_not_leave_this_field_blank)
                } else if (password.length < 8) {
                    etPassword.error =
                        resources.getString(R.string.password_must_be_at_least_8_characters)
                } else {
                    viewModel.setValidState(isValidPassword = true)
                }
            }
        }
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.apply {
            signUpResponse.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Response.Loading -> {
                        showHideLoading(true)
                    }

                    is Response.Success -> {
                        showHideLoading(false)
                        Snackbar.make(
                            binding.root,
                            getString(R.string.sign_up_success),
                            Snackbar.LENGTH_SHORT
                        ).show()

                        appNavigation.openRegisterToHomeScreen()
                    }

                    is Response.Error -> {
                        showHideLoading(false)
                        when (response.e) {
                            is FirebaseAuthUserCollisionException -> {
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.email_already_exists),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            is FirebaseNetworkException -> {
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.error_no_internet),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                            else -> {
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.error_sign_up_failed),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }


}