package com.marslan.stocktracking.dagger.module

import android.content.Context
import androidx.room.Room
import com.marslan.stocktracking.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) = AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Singleton
    @Provides
    fun provideInvoiceDao(database: AppDatabase) = database.invoiceDao()

    @Singleton
    @Provides
    fun provideCustomerDao(database: AppDatabase) = database.customerDao()
}