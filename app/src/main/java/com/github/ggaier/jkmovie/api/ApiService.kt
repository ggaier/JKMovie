package com.github.ggaier.jkmovie.api

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface ApiService {


    /**
     * Get popular movies based on language and region .
     */
    @GET("movie/popular")
    fun fetchPopularMovies(@Query("language") language: String?,
                           @Query("page") page: Int,
                           @Query("region") region: String?):
            LiveData<ApiHttpResponse<List<Video>>>


    /**
     * Get movie details by movie id.
     */
    fun fetchMovieDetails():LiveData<ApiHttpResponse<Video>>


}