package com.github.ggaier.jkmovie.ui.movies

import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.ui.base.PresenterIn
import com.github.ggaier.jkmovie.ui.base.ViewIn

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface MoviesView : ViewIn {

    fun showPopularMovies(movies: List<Video>)

}

interface MoviesPresenterIn : PresenterIn<MoviesView> {

    fun init(tag: String = "", language: String = "En", page: Int = 1)

}