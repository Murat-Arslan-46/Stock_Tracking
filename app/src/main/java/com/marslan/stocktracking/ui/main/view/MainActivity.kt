package com.marslan.stocktracking.ui.main.view

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphNavigator
import androidx.navigation.NavHostController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
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
    private val fragments = listOf(
        HomeFragment.newInstance(),
        OrderFragment.newInstance(),
        ProductFragment.newInstance(),
        CustomerFragment.newInstance()
    )

    private val stock = arrayListOf<Int>()

    @Inject
    lateinit var viewModel: MainViewModel

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment(fragments.first())
        setListeners()

        observeData()
    }

    private fun observeData() {
        viewModel.observeProduct()
        viewModel.observeCustomer()
        viewModel.observeOrder()
    }

    private fun setListeners() {
        binding.apply {
            LL0.setOnClickListener { setBottomSelectItem(0) }
            LL1.setOnClickListener { setBottomSelectItem(1) }
            LL2.setOnClickListener { setBottomSelectItem(2) }
            LL3.setOnClickListener { setBottomSelectItem(3) }
        }
    }

    private fun setBottomSelectItem(index : Int){
        binding.IV0.imageTintList = ColorStateList.valueOf(getColor(R.color.grey_text))
        binding.TV0.setTextColor(getColor(R.color.grey_text))
        binding.IV1.imageTintList = ColorStateList.valueOf(getColor(R.color.grey_text))
        binding.TV1.setTextColor(getColor(R.color.grey_text))
        binding.IV2.imageTintList = ColorStateList.valueOf(getColor(R.color.grey_text))
        binding.TV2.setTextColor(getColor(R.color.grey_text))
        binding.IV3.imageTintList = ColorStateList.valueOf(getColor(R.color.grey_text))
        binding.TV3.setTextColor(getColor(R.color.grey_text))
        when(index){
            0 -> {
                binding.IV0.imageTintList = ColorStateList.valueOf(getColor(R.color.color_primary))
                binding.TV0.setTextColor(getColor(R.color.color_primary))
                openFragment(fragments[0])
            }
            1 -> {
                binding.IV1.imageTintList = ColorStateList.valueOf(getColor(R.color.color_primary))
                binding.TV1.setTextColor(getColor(R.color.color_primary))
                openFragment(fragments[1])
            }
            2 -> {
                binding.IV2.imageTintList = ColorStateList.valueOf(getColor(R.color.color_primary))
                binding.TV2.setTextColor(getColor(R.color.color_primary))
                openFragment(fragments[2])
            }
            3 -> {
                binding.IV3.imageTintList = ColorStateList.valueOf(getColor(R.color.color_primary))
                binding.TV3.setTextColor(getColor(R.color.color_primary))
                openFragment(fragments[3])
            }
        }
    }

    private fun openFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        val index = fragments.indexOf(fragment)
        stock.removeIf { it == index }
        stock.add(fragments.indexOf(fragment))
        Log.d("last index", "openFragment: ${stock.last()}")
        mFragment = fragment
    }

    override fun onBackPressed() {
        if (stock.size > 1){
            stock.removeAt(stock.lastIndex)
            setBottomSelectItem(stock.last())
        }
        else{
            if (mFragment.javaClass.simpleName == HomeFragment::class.java.simpleName)
                finish()
            else {
                stock.removeAt(stock.lastIndex)
                setBottomSelectItem(0)
            }
        }
    }
}