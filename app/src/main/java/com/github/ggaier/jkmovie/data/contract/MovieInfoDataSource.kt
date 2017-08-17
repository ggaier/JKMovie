package com.github.ggaier.jkmovie.data.contract

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MovieInfoDataSource {

    /**
     * Get movie details by movie id [movieId], and the [language] parameter can use to filer the
     * details.
     * @param movieId id of the movie
     * @param language
     * @param appendToResponse  [Append To Response Details](https://developers.themoviedb
     * .org/3/getting-started/append-to-response)
     */
    fun getMovieInfo(movieId: String, language: String?, appendToResponse: String?):
            LiveData<Video>


}