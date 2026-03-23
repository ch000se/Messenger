package com.ch000se.messenger.core.essentials.exceptions

import com.ch000se.messenger.core.essentials.resources.StringProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StringProviderStore @Inject constructor(
    @PublishedApi
    internal val stringProviders: Map<Class<*>, @JvmSuppressWildcards StringProvider>
) {

    inline operator fun <reified T : StringProvider> invoke(): T {
        return stringProviders[T::class.java] as? T
            ?: throw IllegalStateException("StringProvider of type ${T::class} not found")
    }
}