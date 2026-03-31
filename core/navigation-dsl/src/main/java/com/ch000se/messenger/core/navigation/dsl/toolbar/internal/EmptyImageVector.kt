package com.ch000se.messenger.core.navigation.dsl.toolbar.internal

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.PathFillType

internal val EmptyImageVector = materialIcon("empty") {
    materialPath(pathFillType = PathFillType.EvenOdd) {
        moveTo(0f, 0f)
        horizontalLineTo(24f)
        verticalLineTo(24f)
        horizontalLineTo(0f)
        verticalLineTo(0f)
        close()

        moveTo(1f, 1f)
        horizontalLineTo(23f)
        verticalLineTo(23f)
        horizontalLineTo(1f)
        verticalLineTo(1f)
        close()
    }
}
