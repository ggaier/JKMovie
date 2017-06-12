package com.github.ggaier.jkmovie.data.remote

import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.vo.Video
import io.reactivex.Observable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesRemoteDataSource(val mApiService: ApiService) : MoviesDataSource {

    override fun getPopularMovies(language: String , page: Int,
                                  region: String?): Observable<List<Video>> {
        return mApiService.fetchPopularMovies(language, page, region)
                .map { it.mResults }
    }

}