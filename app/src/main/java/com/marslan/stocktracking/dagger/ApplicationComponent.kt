package com.marslan.stocktracking.dagger

import android.app.Application
import com.marslan.stocktracking.app.StockTrackingApplication
import com.marslan.stocktracking.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    FragmentBuilder::class,
    ApplicationModule::class,
    NetworkModule::class,
    RoomModule::class
])
interface ApplicationComponent : AndroidInjector<StockTrackingApplication> {

    override fun inject(instance: StockTrackingApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: StockTrackingApplication): Builder
        fun build(): ApplicationComponent
    }
}