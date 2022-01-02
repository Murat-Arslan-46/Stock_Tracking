package com.marslan.stocktracking.ui.main.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.database.dao.ProductDao
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.services.Request
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class MainRepository @Inject constructor() {

    @Inject
    lateinit var reference: DatabaseReference

    @Inject
    lateinit var productDao: ProductDao

    @Inject
    lateinit var request: Request

    fun addProduct(id: String, value: Product) = request.addProduct(id,value)

    fun observeProducts(listener: ValueEventListener) = request.observeProductsMap(listener)

    suspend fun createProduct(product: Product) = productDao.createProduct(product)

    fun getProducts() = productDao.getAllProduct()
    fun findProductByID(id: Int) = productDao.findProductByID(id)

    fun updateProduct(product: Product) = productDao.updateProduct(product)

    suspend fun removeAllProduct() = productDao.removeAllProduct()
}