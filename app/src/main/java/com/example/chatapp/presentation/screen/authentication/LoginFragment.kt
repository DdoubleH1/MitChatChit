package com.example.chatapp.presentation.screen.authentication

import Spanner
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.presentation.navigation.AppNavigation
import com.example.chatapp.utils.Prefs
import com.example.chatapp.utils.Response
import com.example.chatapp.utils.validate
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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
        Spanner.spanString(
            binding.tvRegister,
            resources.getString(R.string.tv_register_now),
            resources
        ) {
            appNavigation.openLoginToRegisterScreen()
        }
        Prefs.getInstance(requireContext()).userEmail?.let {
            binding.editTextEmail.setText(it)
        }
        Timber.tag("HoangDH").e("initView: ${viewModel.validator.value}")
    }

    override fun bindingStateView() {
        super.bindingStateView()
        binding.apply {
            editTextEmail.validate { email ->
                if (email.isEmpty()) {
                    editTextEmail.error =
                        resources.getString(R.string.do_not_leave_this_field_blank)
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.error = resources.getString(R.string.invalid_email)
                } else {
                    viewModel.setValidState(isEmailValid = true)
                }
            }
            editTextPassword.validate { password ->
                if (password.isEmpty()) {
                    editTextPassword.error =
                        resources.getString(R.string.do_not_leave_this_field_blank)
                } else if (password.length < 8) {
                    editTextPassword.error =
                        resources.getString(R.string.password_must_be_at_least_8_characters)
                } else {
                    viewModel.setValidState(isPasswordValid = true)
                }
            }
        }
        viewModel.validator.observe(viewLifecycleOwner) { isValid ->
            binding.btnLogin.isEnabled = isValid
        }
        if (viewModel.isLoginValid) {
            appNavigation.openLoginToHomeScreen()
        }
    }

    override fun bindingAction() {
        super.bindingAction()
        viewModel.signInResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Response.Loading -> {
                    showHideLoading(true)
                }

                is Response.Success -> {
                    showHideLoading(false)
                    appNavigation.openLoginToHomeScreen()
                }

                is Response.Error -> {
                    showHideLoading(false)
                    when (result.e) {
                        is FirebaseAuthInvalidUserException -> {
                            Snackbar.make(
                                binding.root,
                                resources.getString(R.string.error_user_not_found),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        is FirebaseAuthInvalidCredentialsException -> {
                            Snackbar.make(
                                binding.root,
                                resources.getString(R.string.error_wrong_password),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        is IllegalArgumentException -> {
                            Snackbar.make(
                                binding.root,
                                resources.getString(R.string.error_email_or_password_is_empty),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        is FirebaseNetworkException -> {
                            Snackbar.make(
                                binding.root,
                                resources.getString(R.string.error_no_internet),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                        else -> {
                            Snackbar.make(
                                binding.root,
                                resources.getString(R.string.error_login_failed),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }

    override fun setOnClick() {
        super.setOnClick()
        binding.btnLogin.setOnClickListener {
            Prefs.getInstance(requireContext()).userEmail = binding.editTextEmail.text.toString()
            viewModel.login(
                binding.editTextEmail.text.toString().trim(),
                binding.editTextPassword.text.toString().trim()
            )
        }

    }

}