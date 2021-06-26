package com.example.jetpack2.data.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
    @PrimaryKey
    val tv_show_id: Int? = 0,
    val tv_show_title: String? = "",
    val tv_show_description: String? = "",
    val tv_show_release: String? = "",
    val tv_show_genre : String? = "",
    val tv_show_duration: String? = "",
    val tv_show_rating : String? = "",
    val tv_show_poster : String? = "",
    var favorite: Boolean = false
)