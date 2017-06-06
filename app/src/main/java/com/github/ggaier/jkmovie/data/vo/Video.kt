package com.github.ggaier.jkmovie.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Created by ggaier
 * jwenbo52@gmail.com
 */
data class Video(val id: String,
                 @SerializedName("vote_count") val voteCount: Int,
                 val isVideo: Boolean,
                 @SerializedName("vote_average") val voteAverage: Float,
                 val title: String,
                 val popularity: Float,
                 @SerializedName("post_path") val postPath: String,
                 @SerializedName("original_language") val originalLanguage: String,
                 @SerializedName("original_title") val originalTitle: String,
                 @SerializedName("genre_ids") val genreIds: Array<String>,
                 @SerializedName("backdrop_path") val backdropPath: String,
                 @SerializedName("adult") val isAdult: Boolean,
                 val overview: String,
                 @SerializedName("release_date") val releaseDate: String)
