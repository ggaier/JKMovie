package com.github.ggaier.jkmovie.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.contract.MovieInfoDataSource
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieInfoRemoteDataSource(val mApiService: ApiService) : MovieInfoDataSource {

    override fun getMovieInfo(movieId: String, language: String?,
                              appendToResponse: String?): LiveData<Video> {
        return Transformations.map(mApiService.fetchMovieInfo(movieId, language,
                appendToResponse), { it.mResults })
    }

}