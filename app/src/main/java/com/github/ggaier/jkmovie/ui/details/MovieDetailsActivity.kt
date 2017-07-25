package com.github.ggaier.jkmovie.ui.details

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.databinding.ActivityMovieDetailsBinding
import com.github.ggaier.jkmovie.ui.activity.BaseActivity

class MovieDetailsActivity : BaseActivity() {

    companion object {

        @JvmStatic
        fun show(context: Context, video: Video) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(INTENT_EXTRA_DATA, video)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        mBinding.movie = intent.getParcelableExtra(INTENT_EXTRA_DATA)
    }
}
