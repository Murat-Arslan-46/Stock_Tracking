package com.marslan.stocktracking.ui.product.data

import com.marslan.stocktracking.database.dao.ProductDao
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.services.Request
import javax.inject.Inject

class ProductRepository @Inject constructor() {

    @Inject
    lateinit var productDao: ProductDao

    @Inject
    lateinit var request: Request

    fun setProduct(id: String, value: Product) = request.addProduct(id, value)

    fun getProducts() = productDao.getAllProduct()
}