package com.github.ggaier.jkmovie.api

import com.google.gson.annotations.SerializedName

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ApiHttpResponse<T>(val page: Int,
                         @SerializedName("total_results") val mTotalResults: Int,
                         @SerializedName("total_pages") val mTotalPages: Int,
                         @SerializedName("results") var mResults: T?) {

    var mCode = 0
        private set
    var mErrorMessage: String = ""
        private set

    init {
        mCode = if (mResults == null) 500 else 200
    }

    constructor(error: Throwable?) : this(0, 0, 0, null) {
        mCode = 500
        mErrorMessage = error?.message ?: "Unknown Error"
    }

    constructor(code: Int, errorMessage: String) : this(0, 0, 0, null) {
        mCode = code
        mErrorMessage = errorMessage
    }

    fun isSuccessful() = mCode in 200..299
}

