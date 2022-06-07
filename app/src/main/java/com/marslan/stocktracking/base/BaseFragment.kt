package com.marslan.stocktracking.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment


abstract class BaseFragment<BN : ViewDataBinding> : DaggerFragment() {

    protected lateinit var binding: BN
    var shouldShowBottomBarView: Boolean = true
    abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (this::binding.isInitialized)
            return binding.root
        else
            binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }


    fun hideKeyboard(v: View){
        val manager = requireActivity().getSystemService(InputMethodManager::class.java) as InputMethodManager
        manager.hideSoftInputFromWindow(v.windowToken,0)
    }

}