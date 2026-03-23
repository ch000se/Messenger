package com.ch000se.messenger.core.common.android

import android.app.Application
import com.ch000se.messenger.core.essentials.logger.Logger
import com.ch000se.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import timber.log.Timber
import javax.inject.Inject

abstract class AbstractApp : Application() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var exceptionToMessageMapper: ExceptionToMessageMapper

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Logger.set(logger)
        ExceptionToMessageMapper.set(exceptionToMessageMapper)
    }
}