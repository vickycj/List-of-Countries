package com.vicky.apps.datapoints.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.textChanges
import com.vicky.apps.datapoints.R
import com.vicky.apps.datapoints.base.BaseFragment
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.data.local.entities.CountryEntity
import com.vicky.apps.datapoints.ui.adapter.DataAdapter
import com.vicky.apps.datapoints.ui.viewmodel.CountryListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_country_list.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CountryListFragment : BaseFragment() {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: CountryListViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun getLayout(): Int = R.layout.fragment_country_list

    override fun viewLoaded() {
        initializeValues()
        inilializingRecyclerView()
        initialiseSearchView()
        setTitle(getString(R.string.list_of_countries))
        viewModel.checkCountOfCountriesAndCallApi()
    }


    private fun initialiseSearchView() {
        compositeDisposable.add(searchField
            .textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel
                    .search(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        updateData(viewModel.getFilteredCountryData())
                    }
            })
    }

    private fun inilializingRecyclerView() {
        recyclerView = recyclerViewList
        recyclerView.layoutManager = LinearLayoutManager(activityContext)
        adapter = DataAdapter(viewModel.getCountryData(), activityContext) {
            onItemClicked(it)
        }
        recyclerView.adapter = adapter
    }

    private fun onItemClicked(it: CountryEntity) {



       /* activityContext.replaceFragment(
            activityContext.getBaseContainerId(),
            CountryDetailsFragment.newInstance(viewModel.frameDataForDetails(it), it.name, it.flag)
        )*/
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(CountryListViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getCountryDataSubscription().observe(viewLifecycleOwner, Observer {
            if (it) {
                successCallback()
            } else {
                failureCallback()
            }
        })

    }


    private fun successCallback() {
        updateData(viewModel.getCountryData())
    }

    private fun updateData(data: List<CountryEntity>) {
        adapter.updateData(data)
    }


    private fun failureCallback() {
        Toast.makeText(activity, "DATA FETCH failed", Toast.LENGTH_LONG).show()
    }


}