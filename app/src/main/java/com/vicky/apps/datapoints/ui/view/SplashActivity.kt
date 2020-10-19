package com.vicky.apps.datapoints.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import com.vicky.apps.datapoints.ui.viewmodel.SplashViewModel
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
        viewModel.setCompositeData(compositeDisposable)

        initialiseValues()
    }

    private fun initialiseValues() {
        viewModel.response.observe(this, Observer {
            if(it){
               moveToListActivity();
            }else{
               Toast.makeText(this, getString(R.string.api_failure_message), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.checkCountOfCountriesAndCallApi()
    }

    private fun moveToListActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}