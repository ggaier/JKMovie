package com.github.ggaier.jkmovie.data.local

import com.github.ggaier.jkmovie.data.contract.MoviesDataSource
import com.github.ggaier.jkmovie.data.vo.Video
import io.reactivex.Observable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MoviesLocalDataSource : MoviesDataSource {

    override fun getPopularMovies(language: String, page: Int,
                                  region: String?): Observable<List<Video>> {
        return Observable.empty()
    }

}