package com.github.ggaier.jkmovie.data.contract

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video
import io.reactivex.Observable

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MoviesDataSource {

    /**
     * Get a list of the current popular movies on TMDb. This list updates daily.
     * 获取当前在TMDB中流行的电影。
     *
     * @param language Pass a ISO 639-1 value to display translated data for the fields that
     *      support it.
     * @param page Specify which page to query.
     * @param region Specify a ISO 3166-1 code to filter release dates.
     */
    fun getPopularMovies(language: String = "En", page: Int = 1, region: String? = "US")
            : LiveData<List<Video>>

}