package com.marslan.stocktracking.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity<BN : ViewDataBinding>: DaggerAppCompatActivity() {

    protected lateinit var binding: BN

    abstract val layoutId: Int

    protected var shouldSetContentView: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (shouldSetContentView) {
            binding = DataBindingUtil.setContentView(this, layoutId)
        }
    }

}