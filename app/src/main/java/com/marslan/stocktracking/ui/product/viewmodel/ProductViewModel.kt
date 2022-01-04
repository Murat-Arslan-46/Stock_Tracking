package com.marslan.stocktracking.ui.product.viewmodel

import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.ui.main.data.MainRepository
import com.marslan.stocktracking.ui.product.data.ProductRepository
import javax.inject.Inject

class ProductViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: ProductRepository

    fun getProducts() = repository.getProducts()

    fun setProduct(id: String, product: Product) = repository.setProduct(id, product)
}