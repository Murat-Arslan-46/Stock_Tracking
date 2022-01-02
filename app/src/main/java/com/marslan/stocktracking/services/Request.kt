package com.marslan.stocktracking.services

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.database.table.Customer
import com.marslan.stocktracking.database.table.Invoice
import com.marslan.stocktracking.database.table.Product
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class Request @Inject constructor(){

    companion object{
        const val rootReference = "myDB"
    }

    @Inject
    lateinit var reference: DatabaseReference

    private fun getCustomerRef() = reference.child(rootReference).child("customer")

    private fun getProductRef() = reference.child(rootReference).child("product")

    private fun getInvoiceRef() = reference.child(rootReference).child("invoice")

    fun observeCustomersMap(listener: ValueEventListener) = getCustomerRef().addValueEventListener(listener)

    fun observeProductsMap(listener: ValueEventListener) = getProductRef().addValueEventListener(listener)

    fun observeInvoicesMap(listener: ValueEventListener) = getInvoiceRef().addValueEventListener(listener)

    fun setCustomersMap(map: HashMap<String,Objects>) = getProductRef().setValue(map)

    fun setProductsMap(map: HashMap<String,Objects>) = getProductRef().setValue(map)

    fun setInvoicesMap(map: HashMap<String,Objects>) = getProductRef().setValue(map)

    fun addCustomer(id: String, value: Customer) = getProductRef().child(id).setValue(value)

    fun addProduct(id: String, value: Product) = getProductRef().child(id).setValue(value)

    fun addInvoice(id: String, value: Invoice) = getProductRef().child(id).setValue(value)
}