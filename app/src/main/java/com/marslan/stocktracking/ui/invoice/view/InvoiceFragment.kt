package com.marslan.stocktracking.ui.invoice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.databinding.FragmentInvoiceBinding

class InvoiceFragment : BaseFragment() {

    companion object{

        @JvmStatic
        fun newInstance() = InvoiceFragment()
    }

    private lateinit var binding : FragmentInvoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInvoiceBinding.inflate(layoutInflater)
        return binding.root
    }
}