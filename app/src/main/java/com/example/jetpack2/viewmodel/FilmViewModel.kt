package com.example.jetpack2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.data.repository.FilmRepository
import com.example.jetpack2.utils.Resource

class FilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    val movie: LiveData<List<MovieEntity>> = filmRepository.getListMovie()
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = filmRepository.getDetailMovie(movieId)

    fun insertMovies(movies: List<MovieEntity>) {
        filmRepository.insertMovies(movies)
    }

    val getMovies: LiveData<Resource<List<MovieEntity>>> = filmRepository.getMovies()

    val getMoviesPaged: LiveData<Resource<PagedList<MovieEntity>>> = filmRepository.getMovieAsPaged()

    fun setFavoriteMovie() {
        getMovie.value?.data?.let {
            val newState = !it.favorite
            filmRepository.setFavoriteMovie(it, newState)
        }
    }

    val movieId = MutableLiveData<Int>()

    fun setMovieId(movieId: Int){
        this.movieId.value = movieId
    }

    val getMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { movieId ->
        filmRepository.getMovie(movieId)
    }
    
    val tvShow: LiveData<List<TvShowEntity>> = filmRepository.getListTvShow()
    fun getTvShowDetail(tvId: Int): LiveData<TvShowEntity> = filmRepository.getDetailTvShow(tvId)

    fun insertTvShows(tvShows: List<TvShowEntity>){
        filmRepository.insertTvShows(tvShows)
    }

    val getTvShows: LiveData<Resource<List<TvShowEntity>>> = filmRepository.getTvShows()

    val getTvShowPaged: LiveData<Resource<PagedList<TvShowEntity>>> = filmRepository.getTvShowAsPaged()

    fun setFavoriteTvShow() {
        getTvShow.value?.data?.let {
            val newState = !it.favorite
            filmRepository.setFavoriteTvShow(it, newState)
        }
    }

    val tvShowId = MutableLiveData<Int>()

    val getTvShow: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId) { tvShowId ->
        filmRepository.getTvShow(tvShowId)
    }
    
    
}