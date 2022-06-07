package com.marslan.stocktracking.ui.order.data

import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.data.remote.OrderApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor() {

    @Inject
    lateinit var service: OrderApi

    fun observe() = service.observe()

    fun setValue(id: String, value: Order) = service.setValue(id, value)
}