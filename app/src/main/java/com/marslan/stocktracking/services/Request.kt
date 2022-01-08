package com.marslan.stocktracking.services

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Invoice
import com.marslan.stocktracking.services.model.Product
import javax.inject.Inject

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

    fun addCustomer(id: String, value: Customer) = getProductRef().child(id).setValue(value)

    fun addProduct(id: String, value: Product) = getProductRef().child(id).setValue(value)

    fun addInvoice(id: String, value: Invoice) = getProductRef().child(id).setValue(value)
}