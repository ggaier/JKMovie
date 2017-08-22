package com.github.ggaier.jkmovie.util

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.api.ApiResponse
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
        val responseType: Type) : CallAdapter<R, LiveData<ApiResponse<R>>> {

    override fun adapt(call: Call<R>?): LiveData<ApiResponse<R>> {
        return object : LiveData<ApiResponse<R>>() {
            val started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call?.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>?,
                                                response: Response<R>?) {
                            postValue(ApiResponse(response))
                        }

                        override fun onFailure(call: Call<R>?, t: Throwable?) {
                            postValue(ApiResponse(t))
                        }

                    })
                }
            }

        }
    }

    override fun responseType(): Type {
        return responseType
    }

}