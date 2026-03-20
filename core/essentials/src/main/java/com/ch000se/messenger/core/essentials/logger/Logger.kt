package com.ch000se.messenger.core.essentials.logger

interface Logger {
    fun d(message: String)
    fun e(exception: Exception, message: String = "Error!")

    companion object : Logger {

        private var instance: Logger = DefaultLogger
        override fun d(message: String) {
            instance.d(message)
        }

        override fun e(exception: Exception, message: String) {
            instance.e(exception, message)
        }

        fun set(logger: Logger) {
            instance = logger
        }

        fun reset() {
            instance = DefaultLogger
        }

    }
}