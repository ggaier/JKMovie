package com.github.ggaier.jkmovie.ui.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.v7.widget.Toolbar
import com.github.ggaier.jkmovie.R
import org.jetbrains.anko.appcompat.v7.navigationIconResource

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
abstract class BaseActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setActivityActionBar(toolbar: Toolbar, homeAsUp: Boolean = false,
                             @DrawableRes homeAsUpIndicator: Int = R.drawable.ic_arrow_back,
                             title: String = "") {
        toolbar.navigationIconResource = homeAsUpIndicator
        toolbar.setNavigationOnClickListener { if (homeAsUp) finish() else onNavigationIconClicked() }
        toolbar.title = title
    }

    fun onNavigationIconClicked() {
    }


}