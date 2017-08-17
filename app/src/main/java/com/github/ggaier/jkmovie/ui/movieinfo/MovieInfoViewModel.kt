package com.github.ggaier.jkmovie.ui.movieinfo

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.github.ggaier.jkmovie.di.Injections
import kotlin.properties.Delegates

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class MovieInfoViewModel(application: Application) : AndroidViewModel(
        application) {

    private val mMovieInfoRepo = Injections.getMovieInfoRepo()
    private var mObservableMovieId = MutableLiveData<String>()
    var mMovieId: String by Delegates.observable("", { _, _, newValue ->
        mObservableMovieId.value = newValue
    })

    val mMovieInfo = Transformations.switchMap(mObservableMovieId, {
        mMovieInfoRepo.getMovieInfo(it, null, null)
    })

}