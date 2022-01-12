package com.marslan.stocktracking.ui.order.data

import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.services.Request
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import javax.inject.Inject

class OrderRepository @Inject constructor() {

    @Inject
    lateinit var request: Request

    fun getOrders() = DataHelper.getOrders()

    fun setOrder(id: String, value: Order) = request.addOrder(id, value)

    fun addOrder(id: String, value: Order) = request.addOrder(id, value)
}