package com.example.chatapp.presentation.screen.home.messages.detailMessage

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDetailMessageBinding
import com.example.chatapp.domain.core.base.BaseFragment

class DetailMessageFragment : BaseFragment<FragmentDetailMessageBinding, DetailMessageViewModel>(R.layout.fragment_detail_message) {

    companion object {
        fun newInstance() = DetailMessageFragment()
    }

    private val viewModel: DetailMessageViewModel by viewModels()
    override fun getVM() = viewModel



}