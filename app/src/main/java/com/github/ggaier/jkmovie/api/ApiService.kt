package com.github.ggaier.jkmovie.api

import android.arch.lifecycle.LiveData
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.data.vo.VideosWrapper
import retrofit2.http.GET
import retrofit2.http.Path
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
            LiveData<ApiResponse<VideosWrapper>>


    /**
     * Get movie details by movie id.
     */
    @GET("movie/{movie_id}")
    fun fetchMovieInfo(@Path("movie_id") movieId: String,
                       @Query("language") language: String?,
                       @Query("append_to_response") appendToResponse: String?):
            LiveData<ApiResponse<Video>>


}