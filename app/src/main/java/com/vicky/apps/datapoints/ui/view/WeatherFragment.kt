package com.vicky.apps.datapoints.ui.view

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import com.vicky.apps.datapoints.ui.viewmodel.WeatherViewModel
import javax.inject.Inject

class WeatherFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: WeatherViewModel

    override fun getLayout(): Int =  R.layout.fragment_weather

    override fun viewLoaded() {
        initializeValues()
    }

    private fun initializeValues() {
        viewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel::class.java)
        viewModel.setCompositeData(compositeDisposable)

        viewModel.getWeatherDataSubscription().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                successCallback(it)
            } else {
                failureCallback()
            }
        })

        viewModel.getWeatherData("13.0827","80.2707")
    }

    private fun failureCallback() {
        Toast.makeText(activityContext, "DATA FETCH failed", Toast.LENGTH_LONG).show()
    }

    private fun successCallback(it: WeatherResponse) {
        it.name?.let { it1 -> Log.d("weather", it1) }
    }
}