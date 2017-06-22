package com.github.ggaier.jkmovie

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
object JKApp : Application() {

    val instance = this

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())

    }

}