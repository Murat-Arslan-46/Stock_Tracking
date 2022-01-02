package com.marslan.stocktracking.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "name",
    var price: Double = 0.0,
    var count: Int = 0
)