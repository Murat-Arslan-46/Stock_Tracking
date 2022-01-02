package com.marslan.stocktracking.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Relation")
data class RelationInvoiceToProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val product: Int,
    val invoice: Int
)