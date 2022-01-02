package com.marslan.stocktracking.ui.customer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.databinding.FragmentCustomerBinding

class CustomerFragment : BaseFragment() {

    private lateinit var binding : FragmentCustomerBinding

    companion object{

        @JvmStatic
        fun newInstance() = CustomerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(layoutInflater)
        return binding.root
    }
}