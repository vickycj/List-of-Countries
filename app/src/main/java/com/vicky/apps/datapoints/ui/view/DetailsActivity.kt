package com.vicky.apps.datapoints.ui.view

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants.COUNTRY_DETAIL_ID
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.common.svgdecoder.SVGUtils
import com.vicky.apps.datapoints.ui.adapter.DetailsAdapter
import com.vicky.apps.datapoints.ui.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel:DetailsViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initializeValues()
        inilializingRecyclerView()
        val id = intent.getIntExtra(COUNTRY_DETAIL_ID,0)
        viewModel.fetchSingleCountryDetails(id)
    }

    private fun inilializingRecyclerView() {
        recyclerView = detailsRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = DetailsAdapter(viewModel.getData(), this)
        recyclerView.adapter = adapter
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if(it){
                successCallback()
            }else{
                failureCallback()
            }
        })
    }

    private fun failureCallback() {

    }

    private fun successCallback() {
        title = viewModel.title
        SVGUtils.getSVGRequestBuilder(this)
            .load(Uri.parse(viewModel.flag))
            .into(flagMainView);

        adapter.updateData(viewModel.getData())
    }
}