package com.marslan.stocktracking.services.model

import java.io.Serializable

class Order : Serializable {

    var id: Int? = null
    var customer: Int? = null
    var trader: String? = null
    var products: List<Product>? = null
    var date: Long? = null

    constructor()

    constructor(id: Int?, customer: Int?, trader: String?, products: List<Product>?, date: Long?){
        this.id = id
        this.customer = customer
        this.trader = trader
        this.products = products
        this.date = date
    }
}