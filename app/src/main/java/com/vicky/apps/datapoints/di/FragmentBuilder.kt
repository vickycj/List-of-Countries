package com.vicky.apps.datapoints.di

import com.vicky.apps.datapoints.ui.view.CountryDetailsFragment
import com.vicky.apps.datapoints.ui.view.CountryListFragment
import com.vicky.apps.datapoints.ui.view.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCountryListFragment(): CountryListFragment

    @ContributesAndroidInjector
    abstract fun  contributeCountryDetailsFragment(): CountryDetailsFragment

    @ContributesAndroidInjector
    abstract fun  contributeWeatherFragment(): WeatherFragment
}