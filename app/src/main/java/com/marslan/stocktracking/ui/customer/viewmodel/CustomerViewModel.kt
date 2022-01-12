package com.marslan.stocktracking.ui.customer.viewmodel

import androidx.lifecycle.viewModelScope
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.ui.customer.data.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomerViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: CustomerRepository

    fun getCustomers() = repository.getCustomers()

    fun setCustomer(id: String, value: Customer) = repository.setCustomer(id, value)

    fun post(value: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(value.id.toString(), value)
        }
    }
}