package com.marslan.stocktracking.ui.main.data

import com.marslan.stocktracking.ui.main.data.remote.MainApi
import javax.inject.Inject

class MainRepository @Inject constructor() {

    @Inject
    lateinit var mainApi: MainApi

    fun observeProducts() = mainApi.observeProducts()

    fun observeCustomers() = mainApi.observeCustomers()

    fun observeInvoices() = mainApi.observeInvoices()

    fun observeOrders() = mainApi.observeOrders()
}