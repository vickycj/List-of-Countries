package com.vicky.apps.datapoints.base

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ahmadrosid.svgloader.SvgLoader
import com.vicky.apps.datapoints.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


open class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector ,
    FragmentManager.OnBackStackChangedListener {

    protected val compositeDisposable by lazy { CompositeDisposable() }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        supportFragmentManager.addOnBackStackChangedListener(this);
        shouldDisplayHomeUp();
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
        SvgLoader.pluck().close();
    }

     override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = fragmentInjector

    fun getBaseContainerId() : Int =  R.id.containerView


    override fun onBackStackChanged() {
        shouldDisplayHomeUp()
    }

    open fun shouldDisplayHomeUp() {
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar!!.setDisplayHomeAsUpEnabled(canGoBack)
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

}