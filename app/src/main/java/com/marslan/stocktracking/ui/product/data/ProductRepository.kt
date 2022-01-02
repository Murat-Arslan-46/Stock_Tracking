package com.marslan.stocktracking.ui.product.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.database.dao.ProductDao
import com.marslan.stocktracking.database.table.Product
import javax.inject.Inject

class ProductRepository @Inject constructor() {

    @Inject
    lateinit var reference: DatabaseReference

    @Inject
    lateinit var productDao: ProductDao

    fun post(value: String) = reference.setValue(value)

    suspend fun createProduct(product: Product) = productDao.createProduct(product)

    fun listener(listener: ValueEventListener) = reference.addValueEventListener(listener)
}