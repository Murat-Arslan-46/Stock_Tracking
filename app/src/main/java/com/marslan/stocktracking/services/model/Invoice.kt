package com.marslan.stocktracking.services.model

import java.io.Serializable

class Invoice : Serializable {

    var id: Int? = null
    var order: Int? = null
    var pay: Double? = null

    constructor()

    constructor(
        id: Int?, order: Int?, pay: Double?){
        this.id = id
        this.order = order
        this.pay = pay
    }
}