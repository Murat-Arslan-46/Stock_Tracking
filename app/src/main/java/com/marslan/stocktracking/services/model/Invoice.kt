package com.marslan.stocktracking.services.model

data class Invoice(
    var id: Int?,
    var customer: Int?,
    var products: List<Product>?,
    var date: Long?,
    var total: Double?,
    var remainder: Double?
)