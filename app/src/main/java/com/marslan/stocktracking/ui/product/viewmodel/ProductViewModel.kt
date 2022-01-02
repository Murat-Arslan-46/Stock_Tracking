package com.marslan.stocktracking.ui.product.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ValueEventListener
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.ui.main.data.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: MainRepository

    fun getProducts() = repository.getProducts()
}