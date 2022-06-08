package com.marslan.stocktracking.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.marslan.contract_acceptor.ContractAcceptor
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseActivity
import com.marslan.stocktracking.databinding.ActivityMainBinding
import com.marslan.stocktracking.ui.customer.view.CustomerFragment
import com.marslan.stocktracking.ui.home.view.HomeFragment
import com.marslan.stocktracking.ui.main.component.FragmentNavigator
import com.marslan.stocktracking.ui.main.viewmodel.MainViewModel
import com.marslan.stocktracking.ui.order.view.OrderFragment
import com.marslan.stocktracking.ui.product.view.ProductFragment
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main

    private lateinit var fragments: List<FragmentNavigator.FragmentModel>

    private lateinit var manager: FragmentNavigator

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setListeners()
    }

    private fun initView() {
        @SuppressLint("UseCompatLoadingForDrawables")
        fragments = listOf(
            FragmentNavigator.FragmentModel(
                HomeFragment.newInstance(),
                getString(R.string.menu_home),
                getDrawable(R.drawable.ic_home)
            ),
            FragmentNavigator.FragmentModel(
                OrderFragment.newInstance(),
                getString(R.string.menu_order),
                getDrawable(R.drawable.ic_invoice)
            ),
            FragmentNavigator.FragmentModel(
                ProductFragment.newInstance(),
                getString(R.string.menu_product),
                getDrawable(R.drawable.ic_product)
            ),
            FragmentNavigator.FragmentModel(
                CustomerFragment.newInstance(),
                getString(R.string.menu_customer),
                getDrawable(R.drawable.ic_customer)
            )
        )
        manager = FragmentNavigator.Builder(supportFragmentManager, binding.container.id)
            .setFragments(fragments)
            .setFragmentChangeCallback {
                binding.bottomNav.setBottomSelectItem(fragments.indexOf(it))
            }
            .build()
        manager.start()
        binding.bottomNav.init(fragments)
    }

    private fun setListeners() {
        binding.apply {
            bottomNav.addClickListener {
                manager.open(it)
            }
        }
    }

    override fun onBackPressed() {
        if (manager.onBackPressed()) {
            super.onBackPressed()
        }
    }
}