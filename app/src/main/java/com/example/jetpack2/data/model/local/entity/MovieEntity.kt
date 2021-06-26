package com.example.jetpack2.data.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val movie_id: Int? = 0,
    val movie_title: String? = "",
    val movie_description: String? = "",
    val movie_release: String? = "",
    val movie_genre: String? = "",
    val movie_duration: String? = "",
    val movie_rating: String? = "",
    val movie_poster: String? = "",
    var favorite: Boolean = false
)
