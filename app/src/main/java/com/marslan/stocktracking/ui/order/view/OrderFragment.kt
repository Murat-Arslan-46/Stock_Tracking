package com.marslan.stocktracking.ui.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.addCustomerScreen
import com.marslan.stocktracking.core.extension.addOrderScreen
import com.marslan.stocktracking.databinding.FragmentOrderBinding
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.viewmodel.OrderViewModel
import java.util.ArrayList
import javax.inject.Inject

class OrderFragment : BaseFragment() {

    companion object {

        @JvmStatic
        fun newInstance() = OrderFragment()
    }

    private lateinit var binding: FragmentOrderBinding

    @Inject
    lateinit var viewModel: OrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        binding.orderRefresh.isRefreshing = true
        viewModel.getOrders()?.let { observer(it) }
        binding.orderRefresh.setOnRefreshListener {
            viewModel.getOrders()?.let { observer(it) }
        }
        return binding.root
    }

    override fun onChangeOrder(){
        viewModel.getOrders()?.let { observer(it) }
    }

    private fun observer(list: List<Order>) {
        binding.list = list as ArrayList
        binding.executePendingBindings()
        binding.orderRefresh.isRefreshing = false
    }

    override fun addButton() {
        requireActivity().addOrderScreen {
            viewModel.post(it)
        }
    }
}