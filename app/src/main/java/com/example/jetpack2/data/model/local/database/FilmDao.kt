package com.example.jetpack2.data.model.local.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.jetpack2.data.model.local.entity.MovieEntity
import com.example.jetpack2.data.model.local.entity.TvShowEntity

@Dao
interface FilmDao {
    @Transaction
    @Query("SELECT * FROM movieentity WHERE movie_id = :movieId")
    fun getMovieById(movieId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateMovie(movie: MovieEntity): Int

    @WorkerThread
    @Query("SELECT * FROM movieentity where favorite = 1")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentity where favorite = 1")
    fun getMovieAsPaged(): DataSource.Factory<Int, MovieEntity>



    @Transaction
    @Query("SELECT * FROM tvshowentity WHERE tv_show_id = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>): LongArray

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: TvShowEntity): Int

    @WorkerThread
    @Query("SELECT * FROM tvshowentity where favorite = 1")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentity where favorite = 1")
    fun getTvShowAsPaged(): DataSource.Factory<Int, TvShowEntity>
}