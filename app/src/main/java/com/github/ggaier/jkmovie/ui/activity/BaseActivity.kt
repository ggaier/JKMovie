package com.github.ggaier.jkmovie.ui.activity

import android.arch.lifecycle.LifecycleActivity
import com.github.ggaier.jkmovie.BuildConfig

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
abstract class BaseActivity : LifecycleActivity() {

    companion object {

        @JvmStatic
        val INTENT_EXTRA_DATA = "${BuildConfig.APPLICATION_ID}.extra.DATA"

    }


}