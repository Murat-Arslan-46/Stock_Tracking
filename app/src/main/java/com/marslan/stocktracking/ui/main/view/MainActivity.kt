package com.marslan.stocktracking.ui.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import com.marslan.stocktracking.R
import com.marslan.stocktracking.base.BaseActivity
import com.marslan.stocktracking.base.BaseFragment
import com.marslan.stocktracking.core.extension.addProductScreen
import com.marslan.stocktracking.core.extension.toast
import com.marslan.stocktracking.databinding.ActivityMainBinding
import com.marslan.stocktracking.ui.customer.view.CustomerFragment
import com.marslan.stocktracking.ui.home.view.HomeFragment
import com.marslan.stocktracking.ui.invoice.view.InvoiceFragment
import com.marslan.stocktracking.ui.main.component.MainToolbar
import com.marslan.stocktracking.ui.main.viewmodel.MainViewModel
import com.marslan.stocktracking.ui.product.view.ProductFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainToolbar.MainToolbarListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mFragment: BaseFragment

    @Inject
    lateinit var viewModel: MainViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mFragment = HomeFragment.newInstance()
        openFragment(getString(R.string.menu_home))
        binding.navView.setCheckedItem(R.id.nav_home)

        binding.toolbar.addListener(this)
        binding.navView.setNavigationItemSelectedListener(this::navigationListener)

        viewModel.observeProduct()
        viewModel.keys.observe(this,{
            it?.apply { toast(name) }
        })
    }

    private fun navigationListener(item: MenuItem) : Boolean{
        openFragment(item.title)
        binding.drawerLayout.close()
        return true
    }

    override fun menuButton() {
        if (!binding.drawerLayout.isOpen)
            binding.drawerLayout.open()
        else
            binding.drawerLayout.close()
    }

    override fun addButton() {
        addProductScreen {
            viewModel.post(it)
        }
    }
    override fun searchChange(search: String?) {}

    private fun openFragment(fragmentTitle: CharSequence){
        binding.toolbar.setTitle(
            if(fragmentTitle == getString(R.string.menu_home))
                getString(R.string.app_name)
            else
                fragmentTitle
        )
        val fragment = when(fragmentTitle){
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
                InvoiceFragment.newInstance()
            }
            else -> {
                HomeFragment.newInstance()
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,fragment)
            .commit()
        mFragment = fragment
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