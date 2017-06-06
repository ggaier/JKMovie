package com.github.ggaier.jkmovie.data.contract

import io.reactivex.Observable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MoviesDataSource {

    fun getPopularMovies(language: String = "", page: Int = 1, region: String?)
            : Observable<List<Video>>

}