package com.github.ggaier.jkmovie.util

import android.arch.lifecycle.LiveData
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
class LiveDataCallAdapter<R>(
        val responseType: Type) : CallAdapter<R, LiveData<ApiHttpResponse<R>>> {


    override fun adapt(call: Call<R>?): LiveData<ApiHttpResponse<R>> {
        return object : LiveData<ApiHttpResponse<R>>() {
            val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call?.enqueue(object : Callback<R> {

                        override fun onFailure(call: Call<R>?, t: Throwable?) {
                            Logger.d("response type $responseType")
                            Logger.e(t!!, "error ${t.cause}")

                        }

                        override fun onResponse(call: Call<R>?, response: Response<R>?) {
                            Logger.d("onResponse ${response?.body()}")
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