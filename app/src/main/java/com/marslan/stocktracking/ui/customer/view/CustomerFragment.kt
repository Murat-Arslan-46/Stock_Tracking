package com.marslan.stocktracking.ui.customer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.addCustomerScreen
import com.marslan.stocktracking.databinding.FragmentCustomerBinding
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.customer.viewmodel.CustomerViewModel
import java.util.*
import javax.inject.Inject

class CustomerFragment : BaseFragment() {

    private lateinit var binding: FragmentCustomerBinding

    @Inject
    lateinit var viewModel: CustomerViewModel

    companion object {

        @JvmStatic
        fun newInstance() = CustomerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(layoutInflater)
        binding.customerRefresh.isRefreshing = true
        binding.toolbar.toolbarTitle.text = getString(R.string.menu_customer)
        viewModel.getCustomers()?.let { observer(it) }
        binding.customerRefresh.setOnRefreshListener {
            viewModel.getCustomers()?.let { observer(it) }
        }
        return binding.root
    }

    override fun onChangeCustomer() {
        viewModel.getCustomers()?.let { observer(it) }
    }

    private fun observer(list: List<Customer>) {
        val array = arrayListOf<Customer>()
        array.addAll(list)
        binding.list = array
        binding.executePendingBindings()
        binding.customerRefresh.isRefreshing = false
    }
}