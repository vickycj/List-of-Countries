package com.vicky.apps.datapoints.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vicky.apps.datapoints.R
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable

open abstract class BaseFragment : Fragment() {

    protected val compositeDisposable by lazy { CompositeDisposable() }

    protected lateinit var activityContext : BaseActivity

    abstract fun getLayout() : Int

    abstract fun viewLoaded()

    protected fun setTitle (title : String) {
        activityContext.title = title
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityContext = activity as BaseActivity
        viewLoaded()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

}