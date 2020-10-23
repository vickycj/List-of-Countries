package com.vicky.apps.datapoints.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.ui.viewmodel.CountryListViewModel
import com.vicky.apps.datapoints.ui.viewmodel.WeatherViewModel
import javax.inject.Inject


class ViewModelProviderFactory @Inject constructor(var repository: Repository, var schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)) {
            return CountryListViewModel(repository, schedulerProvider) as T
        }
        else if(modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            return WeatherViewModel(repository, schedulerProvider) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}