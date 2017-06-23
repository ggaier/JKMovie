package com.github.ggaier.jkmovie.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.api.ApiHttpResponse
import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesRemoteDataSource(val mApiService: ApiService) : MoviesDataSource {


    override fun getPopularMovies(language: String, page: Int,
                                  region: String?): LiveData<List<Video>> {
        val apiResponseLiveData: LiveData<ApiHttpResponse<List<Video>>> = mApiService
                .fetchPopularMovies(language, page, region)
        return Transformations.map(apiResponseLiveData, { it.mResults })

    }

}