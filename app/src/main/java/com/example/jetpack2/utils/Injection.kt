package com.example.jetpack2.utils

import android.app.Application
import com.example.jetpack2.data.model.local.database.FilmDatabase
import com.example.jetpack2.data.model.remote.FilmHelper
import com.example.jetpack2.data.repository.FilmRepository
import com.example.jetpack2.data.repository.LocalRepository
import com.example.jetpack2.data.repository.RemoteRepository

object Injection {
    fun provideRepository(application: Application): FilmRepository? {
        val localRepository = LocalRepository(
            FilmDatabase.getInstance(application).filmDao()
        )
        val remoteRepository = RemoteRepository.getInstance(
            FilmHelper(
                application
            )
        )
        val execute = AppExecutor()
        return remoteRepository?.let { FilmRepository.getInstance(localRepository, it, execute) }
    }
}