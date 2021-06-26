package com.example.jetpack2.data.model.remote.response

data class MovieModelResponse (
    val movieId: Int? = 0,
    val movieTitle: String? = "",
    val movieDescription: String? = "",
    val movieRelease: String?= "",
    val movieGenre: String? = "",
    val movieDuration: String? = "",
    val movieRating : String?= "",
    val moviePoster : String?= ""
    )