package com.example.chatapp.domain.core.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<BD: ViewDataBinding, VM: BaseViewModel>: BaseActivityNotRequiredViewModel<BD>() {
    private lateinit var viewModel: VM
    abstract fun getVM(): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getVM()
    }
}