package com.marslan.stocktracking.ui.customer.data.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.core.helper.AppConstruction
import com.marslan.stocktracking.core.helper.PreferencesHelper
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.core.model.ResultMessage
import com.marslan.stocktracking.services.model.Customer
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerApi @Inject constructor() {

    @Inject
    lateinit var reference: DatabaseReference
    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    fun observe(): Observable<Resource<List<Customer>>> {
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

    fun setValue(id: String, value: Customer): Observable<Resource<Boolean>> {
        return Observable.create { emitter ->
            emitter.onNext(Resource.loading)
            reference.child(preferencesHelper.remoteDB).child(AppConstruction.customer_key)
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