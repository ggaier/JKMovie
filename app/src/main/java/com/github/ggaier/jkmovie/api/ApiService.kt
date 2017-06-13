package com.github.ggaier.jkmovie.api

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface ApiService {


    /**
     * 根据地区和语言获取对应的热门电影。
     */
    @GET("movie/popular")
    fun fetchPopularMovies(language: String?, page: Int,
                           region: String?): Observable<ApiHttpResponse>


}