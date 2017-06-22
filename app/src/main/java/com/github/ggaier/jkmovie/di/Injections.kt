package com.github.ggaier.jkmovie.di

import android.app.Application
import com.github.ggaier.jkmovie.TMDB_BASE_URL
import com.github.ggaier.jkmovie.api.ApiInterceptor
import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.MoviesRepository
import com.github.ggaier.jkmovie.data.local.MoviesLocalDataSource
import com.github.ggaier.jkmovie.data.remote.MoviesRemoteDataSource
import com.github.ggaier.jkmovie.ui.movies.MoviesPresenterIn
import com.github.ggaier.jkmovie.ui.movies.MoviesView
import com.github.ggaier.jkmovie.util.LiveDataCallAdapterFactory
import com.github.ggaier.jkmovie.viewmodel.MovieListModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .client(client.build())
                .build()
                .create(ApiService::class.java)
    }

    fun init(application: Application) {
        mApplication = application
    }


    fun getMoviesPresenter(movieView: MoviesView): MoviesPresenterIn {
        return MovieListModel(mApplication)
    }

    fun getMoviesRepo(): MoviesRepository {
        return MoviesRepository(MoviesRemoteDataSource(mApiService), MoviesLocalDataSource())
    }

}