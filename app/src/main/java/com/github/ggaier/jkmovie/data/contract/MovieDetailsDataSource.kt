package com.github.ggaier.jkmovie.data.contract

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MovieDetailsDataSource {

    /**
     * Get movie details by movie id, and the language parameter can use to filer the
     * details.
     * @param movieId id of the movie
     * @param language
     * @param appendToResponse <a href="https://developers.themoviedb
     * .org/3/getting-started/append-to-response">Append To Response Details</a>
     */
    fun getMovieDetails(movieId: String, language: String?, appendToResponse: String?):
            LiveData<Video>


}