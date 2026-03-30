package com.ch000se.messenger.feature.init.domain.exceptions

import com.ch000se.messenger.feature.init.domain.exceptions.base.AbstractInitAppException
import com.ch000se.messenger.feature.init.domain.resources.InitStringProvider

class DeviceIsRootedException : AbstractInitAppException("Device is rooted") {

    override fun getLocalizedErrorMessage(stringProvider: InitStringProvider): String {
        return stringProvider.deviceIsRootedErrorMessage
    }

}
