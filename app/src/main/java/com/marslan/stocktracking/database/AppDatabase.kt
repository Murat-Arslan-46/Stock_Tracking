package com.marslan.stocktracking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marslan.stocktracking.R
import com.marslan.stocktracking.database.dao.CustomerDao
import com.marslan.stocktracking.database.dao.InvoiceDao
import com.marslan.stocktracking.database.dao.ProductDao
import com.marslan.stocktracking.database.table.Customer
import com.marslan.stocktracking.database.table.Invoice
import com.marslan.stocktracking.database.table.Product
import com.marslan.stocktracking.database.table.RelationInvoiceToProduct

@Database(
    entities = [
        Product::class,
        Invoice::class,
        Customer::class,
        RelationInvoiceToProduct::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun customerDao(): CustomerDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return when (val tempInstance = INSTANCE) {
                null -> synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.packageName
                    ).build()
                    INSTANCE = instance

                    return instance
                }
                else -> tempInstance
            }
        }
    }
}