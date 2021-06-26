package com.example.jetpack2.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor

open class AppExecutor @VisibleForTesting constructor(
    private val diskIO: Executor,
    private val mainThread: Executor
) {
    constructor() : this(DiskIOThreadExecutor(), MainThreadExecutor())

    fun diskIO() : Executor {
        return  diskIO
    }

    fun mainThread(): Executor {
        return mainThread
    }

    private class MainThreadExecutor: Executor {
        private val threadHandler = Handler(Looper.getMainLooper())

        override fun execute(p0: Runnable) {
            threadHandler.post(p0)
        }
    }
}