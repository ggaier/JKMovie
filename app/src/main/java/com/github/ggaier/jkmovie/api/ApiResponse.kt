package com.github.ggaier.jkmovie.api

import retrofit2.Response

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class ApiResponse<T>() {

    var code: Int = 0
        private set
    var body: T? = null
        private set

    constructor(response: Response<T>?) : this() {
        code = response?.code() ?: 500
        body = response?.body()
    }

    constructor(throwable: Throwable?) : this() {
        code = 500
        body = null
    }
}