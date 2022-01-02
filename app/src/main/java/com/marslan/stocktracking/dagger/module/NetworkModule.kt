package com.marslan.stocktracking.dagger.module

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideFirebase() = FirebaseDatabase.getInstance("https://stock-tracking-1a078-default-rtdb.europe-west1.firebasedatabase.app/")

    @Singleton
    @Provides
    fun provideReference(database: FirebaseDatabase) = database.reference
}