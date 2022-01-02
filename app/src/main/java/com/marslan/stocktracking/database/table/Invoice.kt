package com.marslan.stocktracking.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Invoice")
data class Invoice(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var customer: Int = 0,
    var date: Long,
    var total: Double,
    var remainder: Double
)