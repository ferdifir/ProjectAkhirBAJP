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
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.ui.content.DetailActivity
import kotlinx.android.synthetic.main.film_container.view.*

class FavTvShowAdapter(private val context: Context): PagedListAdapter<TvShowEntity, FavTvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.posterFilm
        val cardItem: LinearLayout = itemView.layoutMovFav
        val tvTitle: TextView = itemView.judulFilm
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTvShowAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.film_container, parent, false))

    override fun onBindViewHolder(holder: FavTvShowAdapter.ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.tvTitle.text = tvShow.tv_show_title
            context.let { Glide.with(it).load(context.resources
                .getIdentifier(tvShow.tv_show_poster, "drawable", context.packageName))
                .into(holder.poster) }

            holder.cardItem.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("tvShowId", tvShow.tv_show_id)
                context.startActivity(intent)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tv_show_id == newItem.tv_show_id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}