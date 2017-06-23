package com.github.ggaier.jkmovie.ui.movies

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.databinding.ActivityMoviesBinding
import com.github.ggaier.jkmovie.ui.widget.SpacestemDecoration
import com.github.ggaier.jkmovie.util.load
import com.github.ggaier.jkmovie.viewmodel.MovieListModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.list_item_movie_1.view.*
import org.jetbrains.anko.dip

class MoviesActivity : LifecycleActivity(), MoviesView {

    lateinit var mAdapter: MoviesAdapter
    lateinit var mMoviesModel: MovieListModel
    lateinit var mBinding: ActivityMoviesBinding

    override fun showPopularMovies(movies: List<Video>) {
        mBinding.isLoading = false
        mAdapter.add(movies)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies)
        mMoviesModel = ViewModelProviders.of(this).get(MovieListModel::class.java)
        mBinding.isLoading = true
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = MoviesAdapter(this)
        mBinding.recyclerView.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2) as RecyclerView.LayoutManager
        recycler_view.addItemDecoration(SpacestemDecoration(dip(4)))
        mMoviesModel.setMovieTag(page = 1)
        mMoviesModel.getMovies().observe(this,
                Observer<List<Video>> {
                    Logger.d("videos are $it ")
                    mBinding.isLoading = it == null
                    mAdapter.add(it ?: emptyList())
                })
    }

    class MoviesAdapter(
            val activity: MoviesActivity,
            val mMovies: MutableList<Video> = mutableListOf()) : Adapter<MoviesAdapter.MovieViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
            return MovieViewHolder(
                    LayoutInflater.from(parent?.context).inflate(R.layout.list_item_movie_1,
                            parent, false))
        }

        override fun getItemCount(): Int {
            return mMovies.size
        }

        override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {
            val movie = mMovies[position]
            holder?.itemView?.poster?.load(activity, movie.realBackdropPath)
            holder?.itemView?.title?.text = movie.title
        }


        class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

        fun add(movies: List<Video>) {
            val from = mMovies.size
            mMovies.addAll(movies)
            notifyItemRangeChanged(from, mMovies.size)
        }
    }


}
