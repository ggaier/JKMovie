package com.github.ggaier.jkmovie.data

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.local.MoviesLocalDataSource
import com.github.ggaier.jkmovie.data.remote.MoviesRemoteDataSource
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesRepository(private val mRemoteDS: MoviesRemoteDataSource,
                       private val mLocalDS: MoviesLocalDataSource) : MoviesDataSource {


    override fun getPopularMovies(language: String, page: Int,
                                  region: String? ): LiveData<List<Video>> {
        return mRemoteDS.getPopularMovies(language=language, page=page)
    }

}