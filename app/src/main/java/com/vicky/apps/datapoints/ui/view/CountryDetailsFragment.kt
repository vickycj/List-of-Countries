package com.vicky.apps.datapoints.ui.view

import android.net.Uri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.common.svgdecoder.SVGUtils
import com.vicky.apps.datapoints.ui.adapter.DetailsAdapter
import com.vicky.apps.datapoints.ui.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.fragment_country_details.*
import javax.inject.Inject

class CountryDetailsFragment : BaseFragment() {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: DetailsViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DetailsAdapter

    override fun getLayout(): Int = R.layout.fragment_country_details

    override fun viewLoaded() {
        initializeValues()
        inilializingRecyclerView()
        val id = arguments?.getInt(AppConstants.COUNTRY_DETAIL_ID,0)
        id?.let { viewModel.fetchSingleCountryDetails(it) }
    }

    private fun inilializingRecyclerView() {
        recyclerView = detailsRecycler
        recyclerView.layoutManager = LinearLayoutManager(activityContext)
        adapter = DetailsAdapter(viewModel.getData(), activityContext)
        recyclerView.adapter = adapter
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(DetailsViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(viewLifecycleOwner, Observer {
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
        activityContext.title = viewModel.title
        SVGUtils.getSVGRequestBuilder(activityContext)
            .load(Uri.parse(viewModel.flag))
            .into(flagMainView);

        adapter.updateData(viewModel.getData())
    }
}