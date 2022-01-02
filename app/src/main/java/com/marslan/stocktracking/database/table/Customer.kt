package com.marslan.stocktracking.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var company: String? = null,
    var address: String? = null,
    var phone: String? = null,
    var phone2: String? = null,
    var mail: String? = null
)