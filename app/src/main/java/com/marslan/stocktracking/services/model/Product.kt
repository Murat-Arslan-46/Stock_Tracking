package com.marslan.stocktracking.services.model

import java.io.Serializable

class Product : Serializable{

    var id: Int? = null
    var name: String? = null
    var price: Double? = null
    var count: Int? = null

    constructor()
    constructor(id: Int?, name: String?, price: Double?, count: Int?){
        this.id = id
        this.count = count
        this.name = name
        this.price = price
    }
}