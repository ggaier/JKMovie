package com.github.ggaier.jkmovie.di

import com.github.ggaier.jkmovie.TMDB_BASE_URL
import com.github.ggaier.jkmovie.api.ApiIntercepter
import com.github.ggaier.jkmovie.api.ApiService
import com.github.ggaier.jkmovie.data.MoviesRepository
import com.github.ggaier.jkmovie.data.local.MoviesLocalDataSource
import com.github.ggaier.jkmovie.data.remote.MoviesRemoteDataSource
import com.github.ggaier.jkmovie.ui.movies.MoviesPresenter
import com.github.ggaier.jkmovie.ui.movies.MoviesPresenterIn
import com.github.ggaier.jkmovie.ui.movies.MoviesView
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
class Injections {

    companion object {

        @JvmStatic
        fun getMoviesPresenter(movieView: MoviesView): MoviesPresenterIn {
            return MoviesPresenter(movieView,
                    MoviesRepository(MoviesRemoteDataSource(apiService()), MoviesLocalDataSource()))
        }

        @JvmStatic
        fun apiService(): ApiService {
            val builder = OkHttpClient.Builder()
            builder.addNetworkInterceptor(ApiIntercepter())
            return Retrofit.Builder().baseUrl(TMDB_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .build()
                    .create(ApiService::class.java)
        }

    }

}