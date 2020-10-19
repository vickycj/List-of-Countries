package com.vicky.apps.datapoints.di

import com.vicky.apps.datapoints.ui.view.MainActivity
import com.vicky.apps.datapoints.ui.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity
}