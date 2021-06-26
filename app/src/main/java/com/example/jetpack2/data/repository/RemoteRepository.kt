package com.example.jetpack2.data.repository

import android.os.Handler
import android.os.Looper
import com.example.jetpack2.data.model.remote.FilmHelper
import com.example.jetpack2.data.model.remote.response.MovieModelResponse
import com.example.jetpack2.data.model.remote.response.TvShowModelResponse
import com.example.jetpack2.utils.EspressoIdlingResource

class RemoteRepository(private val filmHelper: FilmHelper) {

    fun getMovie(getMovieCallback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            getMovieCallback.onResponse(filmHelper.getMovieList())
            EspressoIdlingResource.decrement()
        }, 1500)
    }

    interface GetMovieCallback {
        fun onResponse(movieResponse: List<MovieModelResponse>)
    }

    fun getMovieDetail(movieId: Int, getMovieDetailCallback: GetMovieDetailCallback){
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            movieId?.let { filmHelper.getMovieById(it) }?.let { getMovieDetailCallback.onResponse(it) }
            EspressoIdlingResource.decrement()
        }, 1500)
    }

    interface GetMovieDetailCallback {
        fun onResponse(movieDetailResponse: MovieModelResponse)
    }

    fun getTvShow(getTvShowCallback: GetTvShowCallback){
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            getTvShowCallback.onResponse(filmHelper.getTvShowList())
            EspressoIdlingResource.decrement()
        }, 1500)
    }

    interface GetTvShowCallback {
        fun onResponse(tvShowResponse: List<TvShowModelResponse>)
    }

    fun getTvShowDetail(tvShowId: Int, getTvShowDetailCallback: GetTvShowDetailCallback){
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            getTvShowDetailCallback.onResponse(filmHelper.getTvShowById(tvShowId))
            EspressoIdlingResource.decrement()
        }, 1500)
    }

    interface GetTvShowDetailCallback {
        fun onResponse(tvShowDetailResponse: TvShowModelResponse)
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(filmHelper: FilmHelper): RemoteRepository{
            if (INSTANCE == null)
                INSTANCE = RemoteRepository(filmHelper)
            return INSTANCE as RemoteRepository
        }
    }

}