package com.marslan.stocktracking.services.model

import java.io.Serializable

data class Customer(
    var id: Int?,
    var name: String?,
    var company: String?,
    var address: String?,
    var phone: List<String>?,
    var mail: String?
) : Serializable