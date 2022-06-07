package com.marslan.stocktracking.ui.customer.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.addCustomerScreen
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.databinding.FragmentCustomerBinding
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.customer.viewmodel.CustomerViewModel
import com.marslan.stocktracking.ui.main.viewmodel.MainViewModel
import java.util.*
import javax.inject.Inject

class CustomerFragment : BaseFragment<FragmentCustomerBinding>() {

    override val layoutId = R.layout.fragment_customer

    @Inject
    lateinit var viewModel: CustomerViewModel

    companion object {

        @JvmStatic
        fun newInstance() = CustomerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerRefresh.isRefreshing = true
        binding.toolbar.toolbarTitle.text = getString(R.string.menu_customer)
        binding.customerRefresh.setOnRefreshListener {
            binding.customerRefresh.isRefreshing = false
        }
        observeData()
        getData()
    }

    private fun getData(){
        viewModel.observeCustomer()
    }

    private fun observeData() {
        viewModel.customerList.observe(viewLifecycleOwner){
            when (it){
                is Resource.loading -> {}
                is Resource.error -> {}
                is Resource.success -> {
                    observer(it.result)
                }
            }
        }
    }

    private fun observer(list: List<Customer>) {
        val array = arrayListOf<Customer>()
        array.addAll(list)
        binding.list = array
        binding.executePendingBindings()
        binding.customerRefresh.isRefreshing = false
    }
}