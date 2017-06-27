package com.github.ggaier.jkmovie.util

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.api.ApiHttpResponse
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class LiveDataCallAdapter(
        val responseType: Type) : CallAdapter<ApiHttpResponse<R>, LiveData<ApiHttpResponse<R>>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<ApiHttpResponse<R>>?): LiveData<ApiHttpResponse<R>> {
        return object : LiveData<ApiHttpResponse<R>>() {
            val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call?.enqueue(object : Callback<ApiHttpResponse<R>> {
                        override fun onFailure(call: Call<ApiHttpResponse<R>>?, t: Throwable?) {
                            Logger.e(
                                    "response type $responseType, onFailure ${t?.printStackTrace()}")
                            postValue(ApiHttpResponse<R>(t))
                        }

                        override fun onResponse(call: Call<ApiHttpResponse<R>>?,
                                                response: Response<ApiHttpResponse<R>>?) {
                            if (response?.isSuccessful ?: false) {
                                postValue(response?.body())
                            } else {
                                postValue(ApiHttpResponse<R>(response?.code() ?: 500,
                                        response?.errorBody()?.string() ?: "Unknown error"))
                            }
                        }
                    })
                }
            }

        }
    }

}