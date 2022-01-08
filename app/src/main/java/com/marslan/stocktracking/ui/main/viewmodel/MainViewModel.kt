package com.marslan.stocktracking.ui.main.viewmodel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.core.helper.DataHelper
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.ui.main.data.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: MainRepository

    fun observeProduct() = repository.observeProducts(productListener)

    private val productListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = arrayListOf<Product>()
            for(child in snapshot.children) {
                child.getValue(Product::class.java)?.let {
                    list.add(it)
                }
            }
            DataHelper.setProducts(list)
        }

        override fun onCancelled(error: DatabaseError) {}
    }

    fun observeCustomer() = repository.observeCustomers(customerListener)

    private val customerListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = arrayListOf<Customer>()
            for(child in snapshot.children) {
                child.getValue(Customer::class.java)?.let {
                    list.add(it)
                }
            }
            DataHelper.setCustomers(list)
        }

        override fun onCancelled(error: DatabaseError) {}
    }
}