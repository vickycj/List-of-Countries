package com.vicky.apps.datapoints.ui.view
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter

import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.vicky.apps.datapoints.base.AppConstants


class MainActivity : BaseActivity() {



    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:MainViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        initializeValues()
        inilializingRecyclerView()
        viewModel.getCountryBasicInfo()
    }

    private fun inilializingRecyclerView() {
        recyclerView = recyclerViewList
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DataAdapter(viewModel.getCountryData())
        recyclerView.adapter = adapter
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if(it){
                successCallback()
            }else{
                failureCallback()
            }
        })
    }


    private fun successCallback(){
        updateData()
    }

    private fun updateData(){
        adapter.updateData(viewModel.getCountryData())
    }


    private fun failureCallback(){
        Toast.makeText(this,"DATA FETCH failed",Toast.LENGTH_LONG).show()
    }






}
