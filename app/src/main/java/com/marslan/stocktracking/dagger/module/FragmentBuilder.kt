package com.marslan.stocktracking.dagger.module

import com.marslan.stocktracking.ui.customer.view.CustomerFragment
import com.marslan.stocktracking.ui.home.view.HomeFragment
import com.marslan.stocktracking.ui.order.view.OrderFragment
import com.marslan.stocktracking.ui.product.view.ProductFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    abstract fun bindProductFragment() : ProductFragment

    @ContributesAndroidInjector
    abstract fun bindCustomerFragment() : CustomerFragment

    @ContributesAndroidInjector
    abstract fun bindOrderFragment() : OrderFragment
}