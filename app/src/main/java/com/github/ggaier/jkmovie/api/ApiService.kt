package com.github.ggaier.jkmovie.api

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
interface ApiService {


    ///movie/popular
    /**
     * 根据地区和语言获取对应的热门电影。
     */
    fun fetchPopularMovies(language: String?, page: Int = 1, region: String?)


}