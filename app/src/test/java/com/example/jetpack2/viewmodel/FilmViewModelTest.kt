package com.example.jetpack2.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.jetpack2.data.DataDummy
import com.example.jetpack2.data.repository.FilmRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModel: FilmViewModel? = null
    private var data = Mockito.mock(FilmRepository::class.java)

    @Before
    fun setUp(){
        viewModel = FilmViewModel(data)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<List<FilmList>>()
        movie.value = DataDummy.getDummyRemoteMovie()
        Mockito.`when`(data.getListMovie()).thenReturn(movie)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.movie?.observeForever(observer as Observer<List<FilmList>>)
        Mockito.verify(data).getListMovie()
    }

    @Test
    fun getMovieDetail() {
        val movie = MutableLiveData<FilmList>()
        movie.value = DataDummy.getDummyRemoteMovie()[0]
        Mockito.`when`(data.getDetailMovie(movie.value!!.id.toString())).thenReturn(movie)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getMovieDetail(movie.value!!.id.toString())?.observeForever(observer as Observer<FilmList>)
        Mockito.verify(data).getListMovie()

        assertEquals(movie.value!!.id, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.id)
        assertEquals(movie.value!!.title, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.title)
        assertEquals(movie.value!!.tagLine, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.tagLine)
        assertEquals(movie.value!!.overview, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.overview)
        assertEquals(movie.value!!.poster_path, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.poster_path)
        assertEquals(movie.value!!.release_date, viewModel?.getMovieDetail(movie.value!!.id.toString())?.value?.release_date)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<List<FilmList>>()
        tvShow.value = DataDummy.getDummyRemoteMovie()
        Mockito.`when`(data.getListTvShow()).thenReturn(tvShow)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.tvShow?.observeForever(observer as Observer<List<FilmList>>)
        Mockito.verify(data).getListTvShow()
    }

    @Test
    fun getTvShowDetail() {
        val tvShow = MutableLiveData<TvShowDetail>()
        tvShow.value = DataDummy.getTvShowDetail()
        Mockito.`when`(data.getDetailTvShow(tvShow.value!!.id.toString())).thenReturn(tvShow)
        val observer = Mockito.mock(Observer::class.java)
        viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.observeForever(observer as Observer<TvShowDetail>)
        Mockito.verify(data).getListTvShow()

        assertEquals(tvShow.value!!.id, viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.value?.id)
        assertEquals(tvShow.value!!.name, viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.value?.name)
        assertEquals(tvShow.value!!.overview, viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.value?.overview)
        assertEquals(tvShow.value!!.poster_path, viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.value?.poster_path)
        assertEquals(tvShow.value!!.first_air_date, viewModel?.getTvShowDetail(tvShow.value!!.id.toString())?.value?.first_air_date)

    }
}