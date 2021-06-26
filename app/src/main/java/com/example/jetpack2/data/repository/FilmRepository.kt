package com.example.jetpack2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.jetpack2.data.model.NetworkBoundResource
import com.example.jetpack2.data.model.RemoteDataSource
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity
import com.example.jetpack2.data.model.remote.ApiResponse
import com.example.jetpack2.data.model.remote.response.MovieModelResponse
import com.example.jetpack2.data.model.remote.response.TvShowModelResponse
import com.example.jetpack2.utils.AppExecutor
import com.example.jetpack2.utils.Resource

class FilmRepository private constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    val executor: AppExecutor):
    RemoteDataSource {

    override fun getListMovie(): LiveData<List<MovieEntity>> {
        val listMovieMutable = MutableLiveData<List<MovieEntity>>()
        remoteRepository.getMovie(object : RemoteRepository.GetMovieCallback {
            override fun onResponse(movieResponse: List<MovieModelResponse>) {
                val movies = ArrayList<MovieEntity>()
                for (i in movieResponse.indices) {
                    val response = movieResponse[i]
                    val movie = MovieEntity(
                        movie_id = response.movieId,
                        movie_title = response.movieTitle,
                        movie_description = response.movieDescription,
                        movie_release = response.movieRelease,
                        movie_genre = response.movieGenre,
                        movie_duration = response.movieDuration,
                        movie_rating = response.movieRating,
                        movie_poster = response.moviePoster
                    )
                    movies.add(movie)
                }
                listMovieMutable.postValue(movies)
            }
        })
        return listMovieMutable
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> {
        val movieMutable = MutableLiveData<MovieEntity>()
        remoteRepository.getMovieDetail(movieId, object : RemoteRepository.GetMovieDetailCallback {
            override fun onResponse(movieDetailResponse: MovieModelResponse) {
                val movie = MovieEntity(
                    movie_id = movieDetailResponse.movieId,
                    movie_title = movieDetailResponse.movieTitle,
                    movie_description = movieDetailResponse.movieDescription,
                    movie_release = movieDetailResponse.movieRelease,
                    movie_genre = movieDetailResponse.movieGenre,
                    movie_duration = movieDetailResponse.movieDuration,
                    movie_rating = movieDetailResponse.movieRating,
                    movie_poster = movieDetailResponse.moviePoster
                )
                movieMutable.postValue(movie)
            }
        })
        return movieMutable
    }

    override fun getListTvShow(): LiveData<List<TvShowEntity>> {
        val listTvShowMutable = MutableLiveData<List<TvShowEntity>>()
        remoteRepository.getTvShow(object : RemoteRepository.GetTvShowCallback{
            override fun onResponse(tvShowResponse: List<TvShowModelResponse>) {
                val tvShows = ArrayList<TvShowEntity>()
                for (i in tvShowResponse.indices) {
                    val response = tvShowResponse[i]
                    val tvShow = TvShowEntity(
                        tv_show_id = response.tvShowId,
                        tv_show_title = response.tvShowTitle,
                        tv_show_description = response.tvShowDescription,
                        tv_show_release = response.tvShowRelease,
                        tv_show_genre = response.tvShowGenre,
                        tv_show_duration = response.tvShowDuration,
                        tv_show_rating = response.tvShowRating,
                        tv_show_poster = response.tvShowPoster
                    )
                    tvShows.add(tvShow)
                }
                listTvShowMutable.postValue(tvShows)
            }
        })
        return listTvShowMutable
    }

    override fun getDetailTvShow(tvshowId: Int): LiveData<TvShowEntity> {
        val tvShowsMutable = MutableLiveData<TvShowEntity>()

        remoteRepository.getTvShowDetail(tvshowId, object :RemoteRepository.GetTvShowDetailCallback {
            override fun onResponse(tvShowDetailResponse: TvShowModelResponse) {
                val tvShow = TvShowEntity(
                    tv_show_id = tvShowDetailResponse.tvShowId,
                    tv_show_title = tvShowDetailResponse.tvShowTitle,
                    tv_show_description = tvShowDetailResponse.tvShowDescription,
                    tv_show_release = tvShowDetailResponse.tvShowRelease,
                    tv_show_genre = tvShowDetailResponse.tvShowGenre,
                    tv_show_duration = tvShowDetailResponse.tvShowDuration,
                    tv_show_rating = tvShowDetailResponse.tvShowRating,
                    tv_show_poster = tvShowDetailResponse.tvShowPoster
                )
                tvShowsMutable.postValue(tvShow)
            }
        })
        return tvShowsMutable
    }

    override fun getMovies(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieModelResponse>>(executor) {
            override fun loadDataFromDB(): LiveData<List<MovieEntity>> {
                return localRepository.getMovies()
            }

            override fun shouldFetch(data: List<MovieEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getMovie(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieModelResponse>(executor) {

            override fun loadDataFromDB(): LiveData<MovieEntity> {
                return localRepository.getMovieById(movieId)
            }

            override fun shouldFetch(data: MovieEntity): Boolean? {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<MovieModelResponse>>?{
                return null
            }

            override fun saveCallResult(data: MovieModelResponse) {

            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        val runnable = {
            localRepository.setFavoriteMovie(movie, isFavorite)
        }
        executor.diskIO().execute(runnable)
    }

    override fun insertMovies(movies: List<MovieEntity>) {
        val runnable = {
            if (localRepository.getMovies().value.isNullOrEmpty()) {
                localRepository.insertMovies(movies)
            }
        }
        executor.diskIO().execute(runnable)
    }

    override fun getMovieAsPaged(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieModelResponse>>(executor){
            override fun loadDataFromDB(): LiveData<PagedList<MovieEntity>> {
                return LivePagedListBuilder(
                    localRepository.getMovieAsPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<MovieModelResponse>) {

            }

        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowModelResponse>>(executor) {

            override fun loadDataFromDB(): LiveData<List<TvShowEntity>> {
                return localRepository.getTvShows()
            }

            override fun shouldFetch(data: List<TvShowEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<TvShowModelResponse>) {

            }
        }.asLiveData()
    }

    override fun getTvShow(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowModelResponse>(executor) {

            override fun loadDataFromDB(): LiveData<TvShowEntity> {
                return localRepository.getTvShowById(tvShowId)
            }

            override fun shouldFetch(data: TvShowEntity): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<TvShowModelResponse>>? {
                return null
            }

            override fun saveCallResult(data: TvShowModelResponse) {

            }
        }.asLiveData()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        val runnable = {
            localRepository.setFavoriteTvShow(tvShow, isFavorite)
        }
        executor.diskIO().execute(runnable)
    }

    override fun insertTvShows(tvShows: List<TvShowEntity>) {
        val runnable = {
            if (localRepository.getMovies().value.isNullOrEmpty()) {
                localRepository.insertTvShows(tvShows)
            }
        }
        executor.diskIO().execute(runnable)
    }

    override fun getTvShowAsPaged(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowModelResponse>>(executor) {
            override fun loadDataFromDB(): LiveData<PagedList<TvShowEntity>> {
                return LivePagedListBuilder(
                    localRepository.getTvShowAsPaged(),
                    20
                ).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowModelResponse>>>? {
                return null
            }

            override fun saveCallResult(data: List<TvShowModelResponse>) {

            }

        }.asLiveData()
    }

    companion object{
        @Volatile
        private var INSTANCE: FilmRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteRepository,
            appExecutor: AppExecutor): FilmRepository? {
            if (INSTANCE == null){
                synchronized(FilmRepository::class.java){
                    if (INSTANCE == null)
                        INSTANCE = FilmRepository(localRepository, remoteRepository, appExecutor)
                }
            }
            return INSTANCE
        }
    }
}