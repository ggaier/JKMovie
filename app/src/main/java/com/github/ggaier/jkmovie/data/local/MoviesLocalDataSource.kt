package com.github.ggaier.jkmovie.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesLocalDataSource : MoviesDataSource {

    override fun getPopularMovies(language: String, page: Int,
                                  region: String?): LiveData<List<Video>> {
        return MutableLiveData<List<Video>>()
    }

}