package com.github.ggaier.jkmovie.ui.movies

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.ui.base.ViewIn

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MoviesView : ViewIn {

    /**
     * display the movies got from data source.
     * @param movies movies to display
     */
    fun showMovies(movies: List<Video>?)

}

interface MoviesPresenterIn {

    fun setMovieTag(tag: String = "", language: String = "En", page: Int = 1)

    fun getMovies(): LiveData<List<Video>>

}