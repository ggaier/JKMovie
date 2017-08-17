package com.github.ggaier.jkmovie.ui.movieinfo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.di.Injections

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieDetailsViewModel(application: Application) : AndroidViewModel(
        application) {

    private val mMovieInfoRepo = Injections.getMovieInfoRepo()
    private var mObservableMovieId = MutableLiveData<String>()

    val mMovieInfo = Transformations.switchMap(mObservableMovieId, {
        mMovieInfoRepo.getMovieInfo(it,null,null)
    })


}