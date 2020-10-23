package com.vicky.apps.datapoints.ui.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.data.reponse.WeatherResponse
import com.vicky.apps.datapoints.ui.viewmodel.WeatherViewModel
import javax.inject.Inject


class WeatherFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var viewModel: WeatherViewModel

    override fun getLayout(): Int =  R.layout.fragment_weather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun viewLoaded() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activityContext)
        initializeValues()
        intialiseLocationListener()
    }

    private fun intialiseLocationListener() {
        if (checkSelfPermission(
                activityContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                getCurrentLocation(location)
            }
    }

    private fun initializeValues() {
        setTitle(getString(R.string.weather_info))
        viewModel = ViewModelProviders.of(this, factory).get(WeatherViewModel::class.java)
        viewModel.setCompositeData(compositeDisposable)

        viewModel.getWeatherDataSubscription().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                successCallback(it)
            } else {
                failureCallback()
            }
        })
    }

    private fun failureCallback() {
        Toast.makeText(activityContext, "DATA FETCH failed", Toast.LENGTH_LONG).show()
    }

    private fun successCallback(it: WeatherResponse) {
        it.name?.let { it1 -> Log.d("weather", it1) }
    }



    private fun getCurrentLocation(location: Location?) {
        val lat = location?.latitude
        val long = location?.longitude

        lat?. let {
            long?. let {
                viewModel.getWeatherData(lat.toString(), long.toString())
            }
        }
    }
}