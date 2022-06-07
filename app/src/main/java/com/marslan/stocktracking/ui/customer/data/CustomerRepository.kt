package com.marslan.stocktracking.ui.customer.data

import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.ui.customer.data.remote.CustomerApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerRepository @Inject constructor() {

    @Inject
    lateinit var service: CustomerApi

    fun observe() = service.observe()

    fun setValue(id: String, value: Customer) = service.setValue(id, value)
}