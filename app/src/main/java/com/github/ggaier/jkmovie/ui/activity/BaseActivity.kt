package com.github.ggaier.jkmovie.ui.activity

import android.arch.lifecycle.LifecycleActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.annotation.MenuRes
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.github.ggaier.jkmovie.R
import org.jetbrains.anko.appcompat.v7.navigationIconResource

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
abstract class BaseActivity : LifecycleActivity() {

    protected lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun setActivityActionBar(toolbar: Toolbar, homeAsUp: Boolean = true,
                             @DrawableRes homeAsUpIndicator: Int = 0,
                             title: String = "") {
        mToolbar = toolbar
        if (homeAsUpIndicator != 0)
            toolbar.navigationIconResource = homeAsUpIndicator
        if (homeAsUp) {
            toolbar.navigationIconResource = R.drawable.ic_arrow_back
        }
        toolbar.setNavigationOnClickListener { if (homeAsUp) finish() else onNavigationIconClicked() }
        toolbar.title = title
    }

    fun onCreateToolbarOptionsMenu(@MenuRes menuResId: Int) {
        mToolbar?.inflateMenu(menuResId)
        mToolbar.setOnMenuItemClickListener {
            onToolbarMenuSelected(it)
        }
    }

    open protected fun onToolbarMenuSelected(it: MenuItem?): Boolean = false

    fun onNavigationIconClicked() {
    }


}