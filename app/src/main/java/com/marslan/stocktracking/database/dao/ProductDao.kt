package com.marslan.stocktracking.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.marslan.stocktracking.database.table.Product

@Dao
interface ProductDao {

    @Insert(entity = Product::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun createProduct(entity: Product)

    @Update(entity = Product::class)
    fun updateProduct(product: Product)

    @Delete(entity = Product::class)
    suspend fun removeProduct(entity: Product)

    @Query("Delete from Product")
    suspend fun removeAllProduct()

    @Query("select * from Product")
    fun getAllProduct() : LiveData<List<Product>>

    @Query("select * from Product where id = :id")
    fun findProductByID(id: Int): Product?
}