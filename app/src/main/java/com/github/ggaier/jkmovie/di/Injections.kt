package com.github.ggaier.jkmovie.di

import android.app.Application
import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import com.github.ggaier.jkmovie.TMDB_BASE_URL
import com.github.ggaier.jkmovie.api.ApiInterceptor
import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.MovieInfoRepo
import com.github.ggaier.jkmovie.data.MoviesRepo
import com.github.ggaier.jkmovie.data.local.MoviesLocalDataSource
import com.github.ggaier.jkmovie.data.remote.MovieInfoRemoteDataSource
import com.github.ggaier.jkmovie.data.remote.MoviesRemoteDataSource
import com.github.ggaier.jkmovie.ui.movies.MovieListViewModel
import com.github.ggaier.jkmovie.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
object Injections {

    val mApiService: ApiService
    lateinit var mApplication: Application
        private set

    init {
        val client = OkHttpClient.Builder()
        client.addInterceptor(ApiInterceptor())
                .addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                                HttpLoggingInterceptor.Level.BODY))
        mApiService = Retrofit.Builder().baseUrl(TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(client.build())
                .build()
                .create(ApiService::class.java)
    }

    /**
     * call this method at the very start of the app.
     */
    fun init(application: Application) {
        mApplication = application
    }


    fun getMoviesPresenter(lifecycleActivity: LifecycleActivity): MovieListViewModel {
        return ViewModelProviders.of(lifecycleActivity).get(MovieListViewModel::class.java)
    }

    fun getMoviesRepo() = MoviesRepo(MoviesRemoteDataSource(mApiService),
            MoviesLocalDataSource())

    fun getMovieInfoRepo() = MovieInfoRepo(MovieInfoRemoteDataSource(mApiService))
}