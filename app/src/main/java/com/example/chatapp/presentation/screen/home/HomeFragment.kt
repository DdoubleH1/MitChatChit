package com.example.chatapp.presentation.screen.home

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentHomeBinding
import com.example.chatapp.domain.core.base.BaseFragment
import com.example.chatapp.utils.Prefs
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    private lateinit var navController: NavController
    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    override fun getVM() = viewModel

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        navController = (childFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).findNavController()
        binding.bottomNav.setupWithNavController(navController)
    }



}