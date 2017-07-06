package com.github.ggaier.jkmovie.ui.movies

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.databinding.ActivityMoviesBinding
import com.github.ggaier.jkmovie.di.Injections
import com.github.ggaier.jkmovie.ui.adapters.BaseAdapter
import com.github.ggaier.jkmovie.ui.widget.SpacestemDecoration
import com.github.ggaier.jkmovie.util.load
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.list_item_movie_1.view.*
import org.jetbrains.anko.dip

class MoviesActivity : LifecycleActivity() {

    lateinit var mAdapter: MoviesAdapter
    lateinit var mMoviesModel: MovieListPresenter
    lateinit var mBinding: ActivityMoviesBinding
    private var mStartPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        mMoviesModel = Injections.getMoviesPresenter(this)

        refresh_layout.setColorSchemeResources(R.color.colorPrimary)
        mBinding.isLoading = true
        initRecyclerView()
    }


    private fun initRecyclerView() {
        mAdapter = MoviesAdapter(this)
        mBinding.recyclerView.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 1) as RecyclerView.LayoutManager
        recycler_view.addItemDecoration(SpacestemDecoration(dip(4)))
        mMoviesModel.setMovieTag(page = mStartPage)
        mAdapter.mOnLoadMoreListener = { mMoviesModel.setMovieTag(page = ++mStartPage) }

        mMoviesModel.getMovies().observe(this,
                Observer<List<Video>?> {
                    mBinding.isLoading = false
                    if (it == null || it.isEmpty()) {
                        return@Observer
                    }
                    if (mStartPage > 1) {
                        mAdapter.loadMoreComplete()
                    }
                    mAdapter.addDatas(it)
                })
    }

    class MoviesAdapter(val activity: MoviesActivity,
                        layoutResId: Int = R.layout.list_item_movie_1) :
            BaseAdapter<Video>(defaultLayoutId = layoutResId) {

        override fun bindDefault(holder: BaseViewHolder?, itemData: Video) {
            holder?.itemView?.poster?.load(activity, itemData.realBackdropPath)
            holder?.itemView?.title?.text = itemData.title
        }
    }


}
