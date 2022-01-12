package com.marslan.stocktracking.services.model

data class DataModel(
    var customers: List<Customer>? = null,
    var products: List<Product>? = null,
    var invoices: List<Invoice>? = null,
    var orders: List<Order>? = null,
    var settings: Settings? = null
)
