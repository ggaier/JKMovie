package com.github.ggaier.jkmovie.ui.movies

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ggaier.jkmovie.R
import com.github.ggaier.jkmovie.data.vo.Video
import com.github.ggaier.jkmovie.util.load
import kotlinx.android.synthetic.main.activity_movies.*
import kotlinx.android.synthetic.main.list_item_movie_1.view.*

class MoviesActivity : AppCompatActivity(), MoviesView {

    override fun setProgressIndicator(active: Boolean) {

    }

    override fun showPopularMovies(movies: List<Video>) {
        mAdapter.notifyWith(movies)
    }

    lateinit var mAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        mAdapter = MoviesAdapter(this)
        recycler_view.adapter = mAdapter
        recycler_view.layoutManager = GridLayoutManager(this, 2)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(InsetDrawable(ColorDrawable(Color.WHITE),
                resources.getDimensionPixelSize(R.dimen.spacing_small)))
        recycler_view.addItemDecoration(dividerItemDecoration)
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
            holder?.itemView?.poster?.load(activity, movie?.postPath)
            holder?.itemView?.title?.text = movie.title
        }


        class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)

        fun notifyWith(movies: List<Video>) {
            val from = mMovies.size
            mMovies.addAll(movies)
            notifyItemRangeChanged(from, mMovies.size)
        }
    }


}
