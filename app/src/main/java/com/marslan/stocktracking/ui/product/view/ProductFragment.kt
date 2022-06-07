package com.marslan.stocktracking.ui.product.view

import android.os.Bundle
import android.view.View
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.editProductScreen
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.databinding.FragmentProductBinding
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.ui.main.viewmodel.MainViewModel
import com.marslan.stocktracking.ui.product.component.ProductRecyclerView
import com.marslan.stocktracking.ui.product.viewmodel.ProductViewModel
import javax.inject.Inject

class ProductFragment : BaseFragment<FragmentProductBinding>(), ProductRecyclerView.ItemEventListener {

    override val layoutId = R.layout.fragment_product

    @Inject
    lateinit var viewModel: ProductViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productRV.listener = this
        binding.toolbar.toolbarTitle.text = getString(R.string.menu_product)
        binding.productRefresh.isRefreshing = true
        binding.productRefresh.setOnRefreshListener {
            binding.productRefresh.isRefreshing = false
        }
        observeData()
        getData()
    }

    private fun getData(){
        viewModel.observeProduct()
    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner){
            when (it){
                is Resource.loading -> {}
                is Resource.error -> {}
                is Resource.success -> {
                    observer(it.result)
                }
            }
        }
    }

    private fun observer(list: List<Product>){
        val array = arrayListOf<Product>()
        array.addAll(list)
        binding.list = array
        binding.executePendingBindings()
        binding.productRefresh.isRefreshing = false
    }


    companion object {

        @JvmStatic
        fun newInstance() = ProductFragment()
    }

    override fun clickConf(product: Product) {
        requireActivity().editProductScreen(product) {}
    }
}