package com.example.jetpack2.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.jetpack2.data.model.local.database.FilmDao
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity

open class LocalRepository constructor(private val filmDao: FilmDao){
    fun getMovies(): LiveData<List<MovieEntity>> = filmDao.getMovies()

    fun getMovieById(movieId: Int): LiveData<MovieEntity> = filmDao.getMovieById(movieId)

    fun insertMovies(movies:List<MovieEntity>){
        filmDao.insertMovies(movies)
    }

    fun setFavoriteMovie(movie: MovieEntity, isFavorite:Boolean){
        movie.favorite = isFavorite
        filmDao.updateMovie(movie)
    }

    fun getMovieAsPaged(): DataSource.Factory<Int, MovieEntity> {
        return filmDao.getMovieAsPaged()
    }


    fun getTvShows(): LiveData<List<TvShowEntity>> = filmDao.getTvShows()

    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity> = filmDao.getTvShowById(tvShowId)

    fun insertTvShows(tvShows:List<TvShowEntity>){
        filmDao.insertTvShow(tvShows)
    }

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite:Boolean){
        tvShow.favorite = isFavorite
        filmDao.updateTvShow(tvShow)
    }

    fun getTvShowAsPaged(): DataSource.Factory<Int, TvShowEntity> {
        return filmDao.getTvShowAsPaged()
    }

    companion object {

        private var INSTANCE: LocalRepository? = null

        fun getInstance(filmDao: FilmDao): LocalRepository {
            if (INSTANCE == null) {
                INSTANCE =
                    LocalRepository(filmDao)
            }
            return INSTANCE as LocalRepository
        }
    }
}