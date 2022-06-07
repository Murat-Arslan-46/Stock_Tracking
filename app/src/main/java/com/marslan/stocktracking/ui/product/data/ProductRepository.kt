package com.marslan.stocktracking.ui.product.data

import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.ui.product.data.remote.ProductApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor() {

    @Inject
    lateinit var service: ProductApi

    fun observe() = service.observe()

    fun setValue(id: String, value: Product) = service.setValue(id, value)
}