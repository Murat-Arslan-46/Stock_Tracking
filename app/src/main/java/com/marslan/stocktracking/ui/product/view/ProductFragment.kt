package com.marslan.stocktracking.ui.product.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.editProductScreen
import com.marslan.stocktracking.core.extension.toast
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.databinding.FragmentProductBinding
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
        viewModel.getProducts().observe(viewLifecycleOwner,this::observer)
        binding = FragmentProductBinding.inflate(layoutInflater)
        binding.productRV.listener = this
        return binding.root
    }

    private fun observer(list: List<Product>){
        binding.list = list as ArrayList
        binding.executePendingBindings()
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