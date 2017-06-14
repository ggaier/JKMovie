package com.github.ggaier.jkmovie.api

import com.github.ggaier.jkmovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 * intercept the request, add api secret key here.
 */
class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val httpUrl = request.url().newBuilder().addQueryParameter("api_key",
                BuildConfig.TMDB_API_KEY).build()
        return chain.proceed(request.newBuilder().url(httpUrl).build())
    }

}