package com.marslan.stocktracking.dagger.module

import com.marslan.stocktracking.ui.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity() : MainActivity
}