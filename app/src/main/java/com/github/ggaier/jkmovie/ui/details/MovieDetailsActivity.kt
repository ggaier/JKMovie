package com.github.ggaier.jkmovie.ui.details

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.ggaier.jkmovie.BuildConfig
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.databinding.ActivityMovieDetailsBinding
import com.github.ggaier.jkmovie.ui.activity.BaseActivity
import com.github.ggaier.jkmovie.util.load
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetailsActivity : BaseActivity() {

    companion object {

        @JvmStatic
        private val INTENT_EXTRA_DATA = "${BuildConfig.APPLICATION_ID}.extra.DATA"

        @JvmStatic
        fun show(context: Context, video: Video) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_DATA, video)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityMovieDetailsBinding
    private lateinit var mMovie: Video

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        mMovie = intent.getParcelableExtra<Video>(INTENT_EXTRA_DATA)
        setActivityActionBar(toolbar, title = mMovie.title)
        mBinding.movie = mMovie
    }

    override fun onStart() {
        super.onStart()
        video_poster.load(this, mMovie.realPosterPath)
    }



}
