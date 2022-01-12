package com.marslan.stocktracking.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseActivity
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.databinding.ActivityMainBinding
import com.marslan.stocktracking.ui.customer.view.CustomerFragment
import com.marslan.stocktracking.ui.home.view.HomeFragment
import com.marslan.stocktracking.ui.order.view.OrderFragment
import com.marslan.stocktracking.ui.main.viewmodel.MainViewModel
import com.marslan.stocktracking.ui.product.view.ProductFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFragment: BaseFragment

    @Inject
    lateinit var viewModel: MainViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment(getString(R.string.menu_home))
        binding.navView.setCheckedItem(R.id.nav_home)
        setListeners()

        observeData()
    }

    private fun observeData() {
        viewModel.observeProduct()
        viewModel.observeCustomer()
        viewModel.observeOrder()
    }

    private fun setListeners() {

        binding.navView.setNavigationItemSelectedListener {
            openFragment(it.title)
            binding.drawerLayout.close()
            true
        }
    }

    fun toggleMenu() {
        if (binding.drawerLayout.isOpen)
            binding.drawerLayout.close()
        else
            binding.drawerLayout.open()
    }

    private fun openFragment(fragmentTitle: CharSequence) {
        binding.toolbar.setTitle(
            if (fragmentTitle == getString(R.string.menu_home))
                getString(R.string.app_name)
            else
                fragmentTitle
        )
        val fragment = when (fragmentTitle) {
            getString(R.string.menu_product) -> {
                ProductFragment.newInstance()
            }
            getString(R.string.menu_home) -> {
                HomeFragment.newInstance()
            }
            getString(R.string.menu_customer) -> {
                CustomerFragment.newInstance()
            }
            getString(R.string.menu_invoice) -> {
                OrderFragment.newInstance()
            }
            else -> {
                HomeFragment.newInstance()
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        mFragment = fragment
        binding.toolbar.addListener(mFragment)
    }

    override fun onBackPressed() {
        if (mFragment.javaClass.simpleName == HomeFragment::class.simpleName)
            finish()
        else {
            openFragment(getString(R.string.menu_home))
            binding.navView.setCheckedItem(R.id.nav_home)
        }
    }
}