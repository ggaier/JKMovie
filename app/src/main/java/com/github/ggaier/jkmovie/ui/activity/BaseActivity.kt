package com.github.ggaier.jkmovie.ui.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.widget.Toolbar
import com.github.ggaier.jkmovie.R

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
abstract class BaseActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setActivityActionBar(toolbar: Toolbar, homeAsUp: Boolean = true,
                             @DrawableRes homeAsUpIndicator: Int = R.drawable.ic_arrow_back,
                             title: String = "") {
        setActionBar(toolbar)
        actionBar?.title = title
        actionBar?.setDisplayHomeAsUpEnabled(homeAsUp)
        actionBar?.setHomeAsUpIndicator(homeAsUpIndicator)
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

}