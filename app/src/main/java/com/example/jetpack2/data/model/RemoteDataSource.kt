package com.example.jetpack2.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.utils.Resource

interface RemoteDataSource {
    fun getListMovie(): LiveData<List<MovieEntity>>
    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>
    fun getListTvShow(): LiveData<List<TvShowEntity>>
    fun getDetailTvShow(tvshowId: Int): LiveData<TvShowEntity>

    fun getMovies(): LiveData<Resource<List<MovieEntity>>>?
    fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>>?
    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean)
    fun insertMovies(movies: List<MovieEntity>)
    fun getMovieAsPaged(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>>?
    fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>>?
    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean)
    fun insertTvShows(tvShows: List<TvShowEntity>)
    fun getTvShowAsPaged(): LiveData<Resource<PagedList<TvShowEntity>>>
}