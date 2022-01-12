package com.marslan.stocktracking.services.model

import java.io.Serializable

class Customer : Serializable{

    var id: Int? = null
    var name: String? = null
    var company: String? = null
    var address: String? = null
    var phone: List<String>? = null
    var mail: String? = null

    constructor()
    constructor( id: Int?, name: String?, company: String?, address: String?, phone: List<String>?, mail: String?){
        this.id = id
        this.name = name
        this.company = company
        this.address = address
        this.phone = phone
        this.mail = mail
    }
}