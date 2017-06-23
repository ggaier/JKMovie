package com.github.ggaier.jkmovie.util;

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */

fun ImageView.load(fragment: Fragment, url: String) {
    this.realLoad(Glide.with(fragment), url)
}

fun ImageView.load(activity: Activity, url: String) {
    this.realLoad(Glide.with(activity), url)
}

fun ImageView.load(fragmentActivity: FragmentActivity, url: String) {
    this.realLoad(Glide.with(fragmentActivity), url)
}

fun ImageView.load(context: Context, url: String) {
    this.realLoad(Glide.with(context), url)
}

fun ImageView.load(url: String) {
    this.realLoad(Glide.with(this), url)
}

fun ImageView.realLoad(requestManager: RequestManager, url: String) {
    val requestOptions = RequestOptions().centerCrop().placeholder(ColorDrawable(Color.GRAY))
            .error(ColorDrawable(Color.DKGRAY))
    requestManager.load(url).apply(requestOptions).into(this)
}