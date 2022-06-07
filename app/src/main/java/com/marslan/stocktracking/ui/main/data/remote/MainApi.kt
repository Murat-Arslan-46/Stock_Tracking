package com.marslan.stocktracking.ui.main.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.core.helper.AppConstruction
import com.marslan.stocktracking.core.helper.PreferencesHelper
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.core.model.ResultMessage
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Invoice
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.services.model.Product
import io.reactivex.Observable
import javax.inject.Inject

class MainApi @Inject constructor() {

    @Inject
    lateinit var reference: DatabaseReference
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    fun observeProducts(): Observable<Resource<List<Product>>>{
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.product_key)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Product>()
                    for (child in snapshot.children) {
                        child.getValue(Product::class.java)?.let {
                            list.add(it)
                        }
                    }
                    emitter.onNext(Resource.success(list,null))
                }

                override fun onCancelled(error: DatabaseError) {
                    emitter.onNext(Resource.error(ResultMessage(error.message,"cancelled","fail")))
                }
            })
        }
    }

    fun observeCustomers(): Observable<Resource<List<Customer>>>{
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.customer_key)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Customer>()
                        for (child in snapshot.children) {
                            child.getValue(Customer::class.java)?.let {
                                list.add(it)
                            }
                        }
                        emitter.onNext(Resource.success(list,null))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onNext(Resource.error(ResultMessage(error.message,"cancelled","fail")))
                    }
                })
        }
    }

    fun observeInvoices(): Observable<Resource<List<Invoice>>>{
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.invoice_key)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Invoice>()
                        for (child in snapshot.children) {
                            child.getValue(Invoice::class.java)?.let {
                                list.add(it)
                            }
                        }
                        emitter.onNext(Resource.success(list,null))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onNext(Resource.error(ResultMessage(error.message,"cancelled","fail")))
                    }
                })
        }
    }

    fun observeOrders(): Observable<Resource<List<Order>>>{
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.order_key)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = arrayListOf<Order>()
                        for (child in snapshot.children) {
                            child.getValue(Order::class.java)?.let {
                                list.add(it)
                            }
                        }
                        emitter.onNext(Resource.success(list,null))
                    }

                    override fun onCancelled(error: DatabaseError) {
                        emitter.onNext(Resource.error(ResultMessage(error.message,"cancelled","fail")))
                    }
                })
        }
    }
}