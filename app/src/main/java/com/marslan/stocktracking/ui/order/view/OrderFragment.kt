package com.marslan.stocktracking.ui.order.view

import android.os.Bundle
import android.view.View
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.databinding.FragmentOrderBinding
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.viewmodel.OrderViewModel
import javax.inject.Inject

class OrderFragment : BaseFragment<FragmentOrderBinding>() {

    override val layoutId = R.layout.fragment_order

    companion object {

        @JvmStatic
        fun newInstance() = OrderFragment()
    }

    @Inject
    lateinit var viewModel: OrderViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderRefresh.isRefreshing = true
        binding.toolbar.toolbarTitle.text = getString(R.string.menu_order)
        binding.orderRefresh.setOnRefreshListener {
            binding.orderRefresh.isRefreshing = false
        }
        observeData()
        getData()
    }

    private fun getData(){
        viewModel.observeOrder()
    }

    private fun observeData() {
        viewModel.orderList.observe(viewLifecycleOwner){
            when (it){
                is Resource.loading -> {}
                is Resource.error -> {}
                is Resource.success -> {
                    observer(it.result)
                }
            }
        }
    }

    private fun observer(list: List<Order>) {
        val array = arrayListOf<Order>()
        array.addAll(list)
        binding.list = array
        binding.executePendingBindings()
        binding.orderRefresh.isRefreshing = false
    }
}