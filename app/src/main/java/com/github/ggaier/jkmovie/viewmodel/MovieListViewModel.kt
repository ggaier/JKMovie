package com.github.ggaier.jkmovie.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.graphics.Movie
import com.github.ggaier.jkmovie.data.MoviesRepository
import com.github.ggaier.jkmovie.ui.movies.MoviesPresenterIn
import com.github.ggaier.jkmovie.ui.movies.MoviesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
open class MovieListViewModel(val mMoviesView: MoviesView,
                              val mMoviesRepository: MoviesRepository, application: Application?)
    : MoviesPresenterIn, AndroidViewModel(application) {

    private val mObservableMovies:LiveData<List<Movie>> =mMoviesRepository.

    override fun onUnsubscribe() {

    }

    override fun init(tag: String, language: String, page: Int) {
        mMoviesRepository.getPopularMovies(language = language, page = page, region = "US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mMoviesView.showPopularMovies(it)
                })
    }

}