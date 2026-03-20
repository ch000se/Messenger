package com.ch000se.messenger.core.common.android

import android.app.Application
import com.ch000se.messenger.core.common.android.logger.AndroidLogger
import com.ch000se.messenger.core.essentials.logger.Logger
import timber.log.Timber

abstract class AbstractApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Logger.set(AndroidLogger())
    }
}