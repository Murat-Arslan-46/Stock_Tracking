package com.marslan.stocktracking.dagger.module

import android.content.Context
import com.marslan.stocktracking.app.StockTrackingApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindContext(application: StockTrackingApplication) : Context
}