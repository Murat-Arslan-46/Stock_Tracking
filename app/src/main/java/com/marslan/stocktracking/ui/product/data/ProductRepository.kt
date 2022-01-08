package com.marslan.stocktracking.ui.product.data

import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.services.Request
import com.marslan.stocktracking.services.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor() {

    @Inject
    lateinit var request: Request

    fun setProduct(id: String, value: Product) = request.addProduct(id, value)

    fun getProducts() = DataHelper.getProducts()

    fun addProduct(id: String, value: Product) = request.addProduct(id, value)
}