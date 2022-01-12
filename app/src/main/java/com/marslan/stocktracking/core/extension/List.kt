package com.marslan.stocktracking.core.extension

import com.marslan.stocktracking.services.model.Customer
import com.marslan.stocktracking.services.model.Product

fun List<Customer>?.findItemByID(id: Int) : Customer?{
    return when {
        this == null -> null
        any { it.id == id } -> first { it.id == id }
        else -> null
    }
}

fun List<Product>?.totalPrice() : Double {
    return if (this == null || size == 0)
        0.0
    else{
        var result = 0.0
        forEach {
            result = result.plus((it.price ?: 0.0) * (it.count ?: 0))
        }
        result
    }
}