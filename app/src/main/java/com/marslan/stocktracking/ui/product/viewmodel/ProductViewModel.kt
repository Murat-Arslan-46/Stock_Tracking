package com.marslan.stocktracking.ui.product.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marslan.stocktracking.base.BaseViewModel
import com.marslan.stocktracking.core.extension.subs
import com.marslan.stocktracking.core.model.Resource
import com.marslan.stocktracking.services.model.Product
import com.marslan.stocktracking.ui.product.data.ProductRepository
import io.reactivex.rxkotlin.addTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var repository: ProductRepository

    val productList = MutableLiveData<Resource<List<Product>>>()

    fun observeProduct() =
        repository
            .observe()
            .subs(productList,"productList get error")
            .addTo(disposeBag)

    fun setProduct(id: String, product: Product) = repository.setValue(id, product)

}