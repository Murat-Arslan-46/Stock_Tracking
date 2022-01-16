package com.marslan.stocktracking.ui.product.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.addProductScreen
import com.marslan.stocktracking.core.extension.editProductScreen
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.core.helper.DataLiveHelper
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.databinding.FragmentProductBinding
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.product.component.ProductRecyclerView
import com.marslan.stocktracking.ui.product.viewmodel.ProductViewModel
import java.util.ArrayList
import javax.inject.Inject

class ProductFragment : BaseFragment(), ProductRecyclerView.ItemEventListener {

    private lateinit var binding: FragmentProductBinding

    @Inject
    lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(layoutInflater)
        binding.productRV.listener = this
        binding.productRefresh.isRefreshing = true
        viewModel.getProducts()?.let { observer(it) }
        binding.productRefresh.setOnRefreshListener {
            viewModel.getProducts()?.let { observer(it) }
        }
        return binding.root
    }

    override fun onChangeProduct() {
        viewModel.getProducts()?.let { observer(it) }
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
        requireActivity().editProductScreen(product) {
            viewModel.setProduct(it.id.toString(),it)
        }
    }
}