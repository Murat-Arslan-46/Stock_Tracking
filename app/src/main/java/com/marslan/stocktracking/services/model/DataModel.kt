package com.marslan.stocktracking.services.model

data class DataModel(
    var customers: List<Customer>? = null,
    var products: List<Product>? = null,
    var invoices: HashMap<String,List<Invoice>>? = null,
    var settings: Settings? = null
)
