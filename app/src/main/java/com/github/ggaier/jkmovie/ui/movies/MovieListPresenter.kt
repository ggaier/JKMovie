package com.github.ggaier.jkmovie.ui.movies

import android.app.Application
import android.arch.lifecycle.*
import com.github.ggaier.jkmovie.data.MoviesRepository
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.di.Injections

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieListPresenter(moviesView: MoviesView, application: Application?)
    : MoviesPresenterIn, AndroidViewModel(application) {

    val mMoviesRepository: MoviesRepository = Injections.getMoviesRepo()
    private val mObservableTags = MutableLiveData<MovieTag>()
    private val mMoviesModel = ViewModelProviders.of(
            moviesView.mLifecycleOwner as LifecycleActivity).get(this::class.java)

    init {
        mMoviesModel.getMovies().observe(moviesView.mLifecycleOwner,
                Observer<List<Video>?> {
                    moviesView.showMovies(it)
                })
    }

    private val mMovies = Transformations.switchMap(mObservableTags, {
        mMoviesRepository.getPopularMovies(it.language, it.page, it.region)
    })

    override fun getMovies(): LiveData<List<Video>> {
        return mMovies
    }

    override fun setMovieTag(region: String, language: String, page: Int) {
        mObservableTags.value = MovieTag(region, language, page)
    }

    data class MovieTag(val region: String, val language: String, val page: Int)

}