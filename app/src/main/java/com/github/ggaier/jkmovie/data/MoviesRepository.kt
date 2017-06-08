package com.github.ggaier.jkmovie.data

import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.local.MoviesLocalDataSource
import com.github.ggaier.jkmovie.data.remote.MoviesRemoteDataSource
import com.github.ggaier.jkmovie.data.vo.Video
import io.reactivex.Observable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesRepository(val mRemoteDS: MoviesRemoteDataSource,
                       val mLocalDS: MoviesLocalDataSource) : MoviesDataSource {


    override fun getPopularMovies(language: String, page: Int,
                                  region: String?): Observable<List<Video>> {

        return mRemoteDS.getPopularMovies(language, page, region)
    }

}