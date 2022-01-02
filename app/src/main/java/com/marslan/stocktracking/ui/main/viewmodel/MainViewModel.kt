package com.marslan.stocktracking.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.ui.main.data.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: MainRepository

    val keys = MutableLiveData<Product?>(null)

    fun post(value: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(value.id.toString(), value)
            repository.createProduct(value)
        }
    }

    fun observeProduct() = repository.observeProducts(productListener)

    private val productListener = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.removeAllProduct()
                for (child in snapshot.children){
                    val product = child.getValue(Product::class.java) ?: continue
                    updateController(product)
                }
            }

        }
        override fun onCancelled(error: DatabaseError) {}
    }

    private suspend fun updateController(product: Product){
            val entity = repository.findProductByID(product.id)
            if (entity == null)
                repository.createProduct(product)
            else
                repository.updateProduct(product)
    }
}