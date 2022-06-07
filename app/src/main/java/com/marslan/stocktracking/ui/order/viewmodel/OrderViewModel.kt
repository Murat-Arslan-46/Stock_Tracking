package com.marslan.stocktracking.ui.order.viewmodel

import androidx.lifecycle.MutableLiveData
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.core.extension.subs
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.services.model.Order
import com.marslan.stocktracking.ui.order.data.OrderRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class OrderViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: OrderRepository

    val orderList = MutableLiveData<Resource<List<Order>>>()

    fun observeOrder() =
        repository
            .observe()
            .subs(orderList,"orderList get error")
            .addTo(disposeBag)

    fun setOrder(id: String, value: Order) = repository.setValue(id, value)
}