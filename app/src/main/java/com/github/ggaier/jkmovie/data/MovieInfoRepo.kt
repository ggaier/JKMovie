package com.github.ggaier.jkmovie.data

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.contract.MovieInfoDataSource
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieInfoRepo(val mRemote: MovieInfoDataSource) : MovieInfoDataSource {

    override fun getMovieInfo(movieId: String, language: String?,
                              appendToResponse: String?): LiveData<Video> {
        return mRemote.getMovieInfo(movieId, language, appendToResponse)
    }

}
