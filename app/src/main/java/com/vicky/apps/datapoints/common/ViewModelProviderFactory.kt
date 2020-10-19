package com.vicky.apps.datapoints.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicky.apps.datapoints.data.Repository
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import com.vicky.apps.datapoints.ui.viewmodel.SplashViewModel
import javax.inject.Inject


class ViewModelProviderFactory @Inject constructor(var repository: Repository, var schedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository, schedulerProvider) as T
        }
        else if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(repository, schedulerProvider) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName())
    }
}