package com.github.ggaier.jkmovie.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ApiHttpResponse<T>(val page: Int,
                         @SerializedName("total_results") val mTotalResults: Int,
                         @SerializedName("total_pages") val mTotalPages: Int,
                         @SerializedName("results") val mResults: T?) {

    constructor(error: Throwable) : this(0,0,0,null) {
    }

    constructor(response: Response<T>) : this(0,0,0,null) {

    }

}

