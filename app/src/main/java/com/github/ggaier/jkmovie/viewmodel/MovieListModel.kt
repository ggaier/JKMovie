package com.github.ggaier.jkmovie.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.data.MoviesRepository
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.ui.movies.MoviesPresenterIn
import com.github.ggaier.jkmovie.ui.movies.MoviesView

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieListModel(val mMoviesView: MoviesView,
                     val mMoviesRepository: MoviesRepository, application: Application?)
    : MoviesPresenterIn, AndroidViewModel(application) {

    private val mObservableTags = MutableLiveData<MovieTag>()

    private val mMovies = Transformations.switchMap(mObservableTags,{
        mMoviesRepository.getPopularMovies(it.language,it.page,it.region)
    })
    override fun getMovies(): LiveData<List<Video>> {
        return mMovies
    }

    override fun setMovieTag(region: String, language: String, page: Int) {
        mObservableTags.value = MovieTag(region, language, page)
    }



    data class MovieTag(val region: String, val language: String, val page: Int)


}