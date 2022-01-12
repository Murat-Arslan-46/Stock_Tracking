package com.marslan.stocktracking.ui.order.viewmodel

import androidx.lifecycle.viewModelScope
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.data.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class OrderViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: OrderRepository

    fun getOrders() = repository.getOrders()

    fun setOrder(id: String, value: Order) = repository.setOrder(id, value)

    fun post(value: Order) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(value.id.toString(), value)
        }
    }
}