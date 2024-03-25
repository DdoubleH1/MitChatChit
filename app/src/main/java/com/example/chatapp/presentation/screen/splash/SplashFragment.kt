package com.example.chatapp.presentation.screen.splash

import androidx.fragment.app.viewModels
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentSplashBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(R.layout.fragment_splash) {

    companion object {
        fun newInstance() = SplashFragment()
    }

    @Inject
    lateinit var appNavigation: AppNavigation

    private val viewModel: SplashViewModel by viewModels()
    override fun getVM() = viewModel

    override fun bindingAction() {
        super.bindingAction()
        viewModel.actionSplash.observe(viewLifecycleOwner){splashState ->
            if (splashState == SplashActionState.Finish){
                appNavigation.openSplashToLoginScreen()
            }
        }
    }


}