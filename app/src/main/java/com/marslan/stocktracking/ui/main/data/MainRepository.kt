package com.marslan.stocktracking.ui.main.data

import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.services.Request
import javax.inject.Inject

class MainRepository @Inject constructor() {

    @Inject
    lateinit var request: Request

    fun observeProducts(listener: ValueEventListener) = request.observeProductsMap(listener)

    fun observeCustomers(listener: ValueEventListener) = request.observeCustomersMap(listener)

    fun observeInvoices(listener: ValueEventListener) = request.observeInvoicesMap(listener)

    fun observeOrders(listener: ValueEventListener) = request.observeOrderMap(listener)
}