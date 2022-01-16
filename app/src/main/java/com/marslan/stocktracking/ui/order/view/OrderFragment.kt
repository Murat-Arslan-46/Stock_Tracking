package com.marslan.stocktracking.ui.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.databinding.FragmentOrderBinding
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.viewmodel.OrderViewModel
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
        binding.toolbar.toolbarTitle.text = getString(R.string.menu_order)
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
        val array = arrayListOf<Order>()
        array.addAll(list)
        binding.list = array
        binding.executePendingBindings()
        binding.orderRefresh.isRefreshing = false
    }
}