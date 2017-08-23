package com.github.ggaier.jkmovie.ui.movieinfo

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.WindowManager
import com.github.ggaier.jkmovie.BuildConfig
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.databinding.ActivityMovieInfoBinding
import com.github.ggaier.jkmovie.di.Injections
import com.github.ggaier.jkmovie.ui.activity.BaseActivity
import com.github.ggaier.jkmovie.util.load
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_movie_info.*

class MovieInfoActivity : BaseActivity() {

    companion object {
        @JvmStatic
        private val INTENT_EXTRA_DATA = "${BuildConfig.APPLICATION_ID}.extra.DATA"

        @JvmStatic
        fun show(context: Context, video: Video) {
            val intent = Intent(context, MovieInfoActivity::class.java)
            intent.putExtra(INTENT_EXTRA_DATA, video)
            context.startActivity(intent)
        }
    }

    private lateinit var mBinding: ActivityMovieInfoBinding
    private lateinit var mPayload: Video
    private lateinit var mMovieInfoModel: MovieInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_movie_info)
        mPayload = intent.getParcelableExtra<Video>(INTENT_EXTRA_DATA)
        setActivityActionBar(toolbar, title = mPayload.title)
        mBinding.movie = mPayload
        mMovieInfoModel = Injections.getMovieInfoViewModel(this)
        mMovieInfoModel.mMovieInfo.observe(this, Observer<Video> {
            Logger.d("movie info $it, genres in string ${it?.genres}")
            mBinding.movie = it
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        video_poster.load(this, mPayload.realPosterPath)
    }


    override fun onResume() {
        super.onResume()
        mMovieInfoModel.mMovieId = mPayload.id
    }

}
