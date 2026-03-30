package com.ch000se.messenger.core.essentials.resources

import javax.inject.Inject
import javax.inject.Singleton

/**
 * This store provides an access to all string providers from any module.
 */
@Singleton
class StringProviderStore @Inject constructor(
    @PublishedApi
    internal val stringProviders: Map<Class<*>, @JvmSuppressWildcards StringProvider>
) {

    /**
     * Get a string provider by its class.
     */
    inline operator fun <reified T : StringProvider> invoke(): T {
        return stringProviders[T::class.java] as T
    }

}
