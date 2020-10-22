package com.vicky.apps.datapoints.ui.view

import android.os.Bundle
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ActivityTheme);
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        launchCountryFragment()
    }

    private fun launchCountryFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        transaction.replace(getBaseContainerId(), CountryListFragment())
        transaction.commit()
    }


}
