package com.example.jetpack2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jetpack2.R
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import kotlinx.android.synthetic.main.item_list.view.*

class TvShowAdapter(private val context: Context?):
    RecyclerView.Adapter<TvShowAdapter.ListViewHolder>() {

    private var listMovies: List<TvShowEntity> = emptyList()
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun addList(movieModel: List<TvShowEntity>){
        this.listMovies = movieModel
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindViewHolder(listMovies[position])
    }

    inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(listMovies: TvShowEntity) {
            with(itemView) {
                tv_title.text = listMovies.tv_show_title
                tv_orgin.text = listMovies.tv_show_rating
                Glide.with(context)
                    .load(context.resources
                        .getIdentifier(listMovies
                            .tv_show_poster,
                            "drawable",
                            context.packageName))
                    .apply(RequestOptions().override(100, 150))
                    .into(img_item_photo)

                item_list_film.setOnClickListener {
                    onItemClickCallback.onItemClicked(listMovies)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowEntity)
    }
}