package com.github.ggaier.jkmovie.ui.movies

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.LifecycleOwner
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

class MoviesActivity : LifecycleActivity(), MoviesView {

    lateinit var mAdapter: MoviesAdapter
    lateinit var mMoviesPresenter: MoviesPresenterIn
    lateinit var mBinding: ActivityMoviesBinding
    private var mStartPage: Int = 1


    override val mLifecycleOwner: LifecycleOwner
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        refresh_layout.setColorSchemeResources(R.color.colorPrimary)
        mBinding.isLoading = true
        mMoviesPresenter = Injections.getMoviesPresenter(this)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        mAdapter = MoviesAdapter(this)
        mBinding.recyclerView.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 1) as RecyclerView.LayoutManager
        recycler_view.addItemDecoration(SpacestemDecoration(dip(4)))
        mMoviesPresenter.setMovieTag(page = mStartPage)
        mAdapter.mOnLoadMoreListener = { mMoviesPresenter.setMovieTag(page = ++mStartPage) }
    }

    override fun showMovies(movies: List<Video>?) {
        mBinding.isLoading = false
        if (movies == null || movies.isEmpty()) {
            return
        }
        if (mStartPage > 1) {
            mAdapter.loadMoreComplete()
        }
        mAdapter.addDatas(movies)
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
