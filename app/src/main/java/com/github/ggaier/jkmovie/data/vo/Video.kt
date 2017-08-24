package com.github.ggaier.jkmovie.data.vo

import android.os.Parcel
import android.os.Parcelable
import com.github.ggaier.jkmovie.TMDB_IMAGE_BASE_URL
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
                 @SerializedName("poster_path") val posterPath: String?,
                 @SerializedName("original_language") val originalLanguage: String?,
                 @SerializedName("original_title") val originalTitle: String,
                 @SerializedName("genre_ids") val genreIds: Array<String>,
                 @SerializedName("backdrop_path") private val backdropPath: String,
                 @SerializedName("adult") val isAdult: Boolean,
                 val overview: String,
                 @SerializedName("release_date") val releaseDate: String,
                 val genres: List<Genre>?,
                 @SerializedName("runtime") val duration: Int?) : Parcelable {

    val realBackdropPath: String
        get() {
            return TMDB_IMAGE_BASE_URL.plus(backdropPath)
        }

    val realPosterPath: String
        get() {
            return TMDB_IMAGE_BASE_URL + posterPath
        }

    val genresInString: String
        get() {
            return genres?.joinToString(separator = " ", postfix = " - " +
                    "$releaseDate · $duration min",
                    limit = 3,
                    transform = { it.name }) ?: ""
        }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Video> = object : Parcelable.Creator<Video> {
            override fun createFromParcel(source: Parcel): Video = Video(source)
            override fun newArray(size: Int): Array<Video?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            1 == source.readInt(),
            source.readFloat(),
            source.readString(),
            source.readFloat(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.createStringArray(),
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            source.createTypedArrayList(Genre.CREATOR),
            source.readValue(Int::class.java.classLoader) as Int?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeInt(voteCount)
        dest.writeInt((if (isVideo) 1 else 0))
        dest.writeFloat(voteAverage)
        dest.writeString(title)
        dest.writeFloat(popularity)
        dest.writeString(posterPath)
        dest.writeString(originalLanguage)
        dest.writeString(originalTitle)
        dest.writeStringArray(genreIds)
        dest.writeString(backdropPath)
        dest.writeInt((if (isAdult) 1 else 0))
        dest.writeString(overview)
        dest.writeString(releaseDate)
        dest.writeTypedList(genres)
        dest.writeValue(duration)
    }
}



