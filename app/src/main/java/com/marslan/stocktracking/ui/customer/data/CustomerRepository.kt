package com.marslan.stocktracking.ui.customer.data

import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.services.Request
import com.marslan.stocktracking.services.model.Customer
import javax.inject.Inject

class CustomerRepository @Inject constructor() {

    @Inject
    lateinit var request: Request

    fun getCustomers() = DataHelper.getCustomers()

    fun setCustomer(id: String, value: Customer) = request.addCustomer(id, value)

    fun addCustomer(id: String, value: Customer) = request.addCustomer(id, value)
}