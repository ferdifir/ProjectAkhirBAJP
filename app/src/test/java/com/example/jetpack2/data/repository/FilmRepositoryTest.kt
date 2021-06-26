package com.example.jetpack2.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jetpack2.data.DataDummy
import com.example.jetpack2.data.LiveDataTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class FilmRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localRepository = Mockito.mock(LocalRepository::class.java)
    private val remoteRepository = Mockito.mock(RemoteRepository::class.java)
    private val dataRepositoryTest = FilmRepository(localRepository, remoteRepository)

    private val movieList = DataDummy.getDummyRemoteMovie()
    private val movieId = DataDummy.getDummyRemoteMovie()[0].id.toString()
    private val tvShowList = DataDummy.getDummyRemoteTvShows()
    private val tvShowId = tvShowList[0].id.toString()
    private val tvShowDetail = DataDummy.getTvShowDetail()

    private fun <T> anyOfT(type: Class<T>): T = Mockito.any(type)
    private fun <T> eqOfT(obj: T): T = Mockito.eq(obj)

    @Test
    fun getMovieDetail() {
        Mockito.doAnswer { invocation ->
            val callback = invocation.arguments[0] as RemoteRepository.GetMovieDetailCallback
            callback.onResponse(movieList[0])
            null
        }.`when`(remoteRepository).getMovieDetail(
            eqOfT(movieId),
            anyOfT(RemoteRepository.GetMovieDetailCallback::class.java)
        )

    }

    @Test
    fun getTvShowDetail() {
        Mockito.doAnswer { invocation ->
            val callback = invocation.arguments[0] as RemoteRepository.GetTvShowDetailCallback
            callback.onResponse(tvShowDetail)
            null
        }.`when`(remoteRepository).getTvShowDetail(eqOfT(tvShowId),
            anyOfT(RemoteRepository.GetTvShowDetailCallback::class.java)
        )
    }

    @Test
    fun getMovie() {
        Mockito.doAnswer { invocation ->
            val callback = invocation.arguments[0] as RemoteRepository.GetMovieCallback
            callback.onResponse(movieList)
            null
        }.`when`(remoteRepository).getMovie(anyOfT(RemoteRepository.GetMovieCallback::class.java))

        val result = LiveDataTest.getValue(dataRepositoryTest.getListMovie())
        assertEquals(movieList.size, result.size)
        assertNotNull(movieList)
    }

    @Test
    fun getTvShow() {
        Mockito.doAnswer { invocation ->
            val callback = invocation.arguments[0] as RemoteRepository.GetTvShowCallback
            callback.onResponse(tvShowList)
            null
        }.`when`(remoteRepository).getTvShow(anyOfT(RemoteRepository.GetTvShowCallback::class.java))

        val result = LiveDataTest.getValue(dataRepositoryTest.getListTvShow())
        assertEquals(tvShowList.size, result.size)
        assertNotNull(tvShowList)
    }
}