package com.marslan.stocktracking.ui.customer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.core.extension.subs
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.ui.customer.data.CustomerRepository
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CustomerViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: CustomerRepository

    val customerList = MutableLiveData<Resource<List<Customer>>>()

    fun observeCustomer() =
        repository
            .observe()
            .subs(customerList,"customerList get error")
            .addTo(disposeBag)

    fun setCustomer(id: String, value: Customer) = repository.setValue(id, value)

}