package com.ch000se.messenger.feature.init.domain.repositories

import java.time.ZonedDateTime

interface DateTimeRepository {
    fun now(): ZonedDateTime
}