package com.github.ggaier.jkmovie.ui.movies

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.data.MoviesRepo
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.di.Injections

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieListViewModel(application: Application?) : AndroidViewModel(application) {

    val mMoviesRepository: MoviesRepo = Injections.getMoviesRepo()
    private val mObservableTags = MutableLiveData<MovieTag>()

    init {

    }

    private val mMovies = Transformations.switchMap(mObservableTags, {
        mMoviesRepository.getPopularMovies(it.language, it.page, it.region)
    })

    fun getMovies(): LiveData<List<Video>> {
        return mMovies
    }

    fun setMovieTag(region: String="US", language: String = "EN", page: Int = 1) {
        mObservableTags.value = MovieTag(region, language, page)
    }


    data class MovieTag(val region: String, val language: String, val page: Int)


}