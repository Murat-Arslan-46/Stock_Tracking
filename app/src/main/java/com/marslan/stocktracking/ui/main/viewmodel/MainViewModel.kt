package com.marslan.stocktracking.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.core.extension.subs
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.services.model.Invoice
import com.marslan.stocktracking.ui.main.data.MainRepository
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: MainRepository

    val invoiceList = MutableLiveData<Resource<List<Invoice>>>()


    fun observeInvoice() =
        repository
            .observeInvoices()
            .subs(invoiceList,"invoiceList get error")
            .addTo(disposeBag)

}