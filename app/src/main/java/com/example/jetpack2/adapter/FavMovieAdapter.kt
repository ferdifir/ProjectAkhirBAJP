package com.example.jetpack2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpack2.R
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.ui.content.DetailActivity
import com.example.jetpack2.utils.Helper.TYPE_MOVIE
import kotlinx.android.synthetic.main.film_container.view.*

class FavMovieAdapter(private val context: Context?): PagedListAdapter<MovieEntity, FavMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.posterFilm
        val title: TextView = itemView.judulFilm
        val itemCard: LinearLayout = itemView.layoutMovFav
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.film_container, parent, false))

    override fun onBindViewHolder(holder: FavMovieAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.title.text = movie.movie_title
            Glide.with(context!!)
                .load(context.resources.getIdentifier(movie.movie_poster, "drawable", context.packageName))
                .into(holder.poster)
            holder.itemCard.setOnClickListener {
                context.startActivity(
                    Intent(context, DetailActivity::class.java)
                        .putExtra(TYPE_MOVIE, movie.movie_id)
                )
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movie_id == newItem.movie_id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}