package com.github.ggaier.jkmovie.util

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.api.ApiHttpResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?,
                     retrofit: Retrofit?): CallAdapter<*, *>? {
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }
        val observableType = getParameterUpperBound(0, returnType as? ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiHttpResponse::class.java) {
            throw IllegalArgumentException(" type must be a resource")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }
//        val bodyType: Type = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter(observableType)

    }

}