package com.marslan.stocktracking.ui.order.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.core.helper.AppConstruction
import com.marslan.stocktracking.core.helper.PreferencesHelper
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.core.model.ResultMessage
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Order
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderApi @Inject constructor() {

    @Inject
    lateinit var reference: DatabaseReference
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    fun observe(): Observable<Resource<List<Order>>> {
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

    fun setValue(id: String, value: Order): Observable<Resource<Boolean>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.order_key)
                .child(id)
                .setValue(value)
                .addOnCompleteListener {
                    emitter.onNext(Resource.success(true,null))
                }
                .addOnSuccessListener {
                    emitter.onNext(Resource.success(false,null))
                }
                .addOnCanceledListener {
                    emitter.onNext(Resource.error(ResultMessage("cancelled","cancelled","fail")))
                }
                .addOnFailureListener {
                    emitter.onNext(Resource.error(ResultMessage(it.message,"failure","fail")))
                }
        }
    }
}