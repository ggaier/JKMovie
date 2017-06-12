package com.github.ggaier.jkmovie.ui.movies

import com.github.ggaier.jkmovie.data.MoviesRepository

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
open class MoviesPresenter(val mMoviesView: MoviesView,
                           val mMoviesRepository: MoviesRepository) : MoviesPresenterIn {

    override fun onUnsubscribe() {

    }

    override fun init(tag: String, language: String, page: Int) {
        mMoviesRepository.getPopularMovies(language,page)
    }

}