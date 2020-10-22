package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import com.vicky.apps.datapoints.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        launchCountryFragment()
    }

    private fun launchCountryFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(getBaseContainerId(), CountryListFragment())
        transaction.commit()
    }


}
