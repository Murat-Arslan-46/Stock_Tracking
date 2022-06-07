package com.marslan.stocktracking.app

import com.marslan.stocktracking.core.helper.PreferencesHelper
import com.marslan.stocktracking.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class StockTrackingApplication : DaggerApplication() {

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    override fun onCreate() {
        super.onCreate()
        preferencesHelper.remoteDB = "myDB"
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
        return applicationComponent
    }
}