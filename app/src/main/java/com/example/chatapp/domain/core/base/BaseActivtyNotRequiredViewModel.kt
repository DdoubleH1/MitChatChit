package com.example.chatapp.domain.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.ref.WeakReference

abstract class BaseActivtyNotRequiredViewModel<BD: ViewDataBinding> : AppCompatActivity() {

    private var _binding: BD? = null
    protected val binding: BD get() = _binding!!

    @get: LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = DataBindingUtil.setContentView(WeakReference(this).get()!!, layoutId)
        _binding?.lifecycleOwner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding?.unbind()
        _binding = null
    }
}