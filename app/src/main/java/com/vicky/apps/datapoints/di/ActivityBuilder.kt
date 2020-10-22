package com.vicky.apps.datapoints.di

import com.vicky.apps.datapoints.ui.view.DetailsActivity
import com.vicky.apps.datapoints.ui.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindDetailsActivity(): DetailsActivity
}