package com.github.ggaier.jkmovie.util;

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */

fun ImageView.load(fragment: Fragment, url: String) {
    Glide.with(fragment).load(url).into(this)
}

fun ImageView.load(activity: Activity, url: String) {
    Glide.with(activity).load(url).into(this)
}

fun ImageView.load(fragmentActivity: FragmentActivity, url: String) {
    Glide.with(fragmentActivity).load(url).into(this)
}

fun ImageView.load(context: Context, url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.load(view: View, url: String) {
    Glide.with(view).load(url).into(this)
}