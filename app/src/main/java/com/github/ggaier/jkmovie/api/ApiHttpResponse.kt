package com.github.ggaier.jkmovie.api

import com.google.gson.annotations.SerializedName

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ApiHttpResponse<T>(val page: Int,
                         @SerializedName("total_results") val mTotalResults: Int,
                         @SerializedName("total_pages") val mTotalPages: Int,
                         @SerializedName("results") var mResults: T?)

