package com.vicky.apps.datapoints.ui.view

import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.AppConstants
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.svgdecoder.SVGUtils
import com.vicky.apps.datapoints.ui.adapter.DetailsAdapter
import com.vicky.apps.datapoints.ui.model.KeyValue
import kotlinx.android.synthetic.main.fragment_country_details.*


class CountryDetailsFragment : BaseFragment() {


    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DetailsAdapter

    override fun getLayout(): Int = R.layout.fragment_country_details

    companion object {
        fun newInstance(it: ArrayList<KeyValue>, title: String, flag: String): CountryDetailsFragment {
            val myFragment = CountryDetailsFragment()
            val args = Bundle()
            args.putString(AppConstants.COUNTRY_TITLE, title)
            args.putString(AppConstants.COUNTRY_FLAG, flag)
            args.putParcelableArrayList(AppConstants.COUNTRY_DETAIL_DATA, it)
            myFragment.arguments = args
            return myFragment
        }
    }

    override fun viewLoaded() {
        inilializingRecyclerView()
        val data : ArrayList<KeyValue>? = arguments?.getParcelableArrayList(AppConstants.COUNTRY_DETAIL_DATA)
        val title = arguments?.getString(AppConstants.COUNTRY_TITLE)
        val flag = arguments?.getString(AppConstants.COUNTRY_FLAG)
        setDataInUI(data,title,flag)
    }


    private fun inilializingRecyclerView() {
        recyclerView = detailsRecycler
        recyclerView.layoutManager = LinearLayoutManager(activityContext)
        adapter = DetailsAdapter(ArrayList(), activityContext)
        recyclerView.adapter = adapter
    }


    private fun setDataInUI(data : ArrayList<KeyValue>?, title: String?, flag: String?) {
        title?.let { setTitle(it) }
        SVGUtils.getSVGRequestBuilder(activityContext)
            .load(Uri.parse(flag))
            .into(flagMainView);
        data?.let { adapter.updateData(it) }
    }
}