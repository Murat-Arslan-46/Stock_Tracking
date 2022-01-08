package com.marslan.stocktracking.ui.product.viewmodel

import androidx.lifecycle.viewModelScope
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.ui.product.data.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: ProductRepository

    fun getProducts() = repository.getProducts()

    fun setProduct(id: String, product: Product) = repository.setProduct(id, product)

    fun post(value: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(value.id.toString(), value)
        }
    }
}