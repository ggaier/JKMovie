package com.github.ggaier.jkmovie.util

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.R
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
        val responseType: Type) : CallAdapter<R, LiveData<R>> {

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>?): LiveData<R> {
        return object : LiveData<R>() {
            val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call?.enqueue(object : Callback<R> {
                        override fun onFailure(call: Call<R>?, t: Throwable?) {
                            Logger.e("response type $responseType, onFailure ${t?.printStackTrace()}")
                            postValue(null)
                        }

                        override fun onResponse(call: Call<R>?,
                                                response: Response<R>?) {
                            if (response?.isSuccessful ?: false) {
                                postValue(response?.body())
                            } else {
                                postValue(null)
                            }
                        }
                    })
                }
            }

        }
    }

}