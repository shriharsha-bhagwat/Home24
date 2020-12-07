package com.home24.assignment.core.utils

import com.home24.assignment.BuildConfig

internal interface DebugConfig {

    val enabled: Boolean
    val logger: MyLogger

    object Default : DebugConfig {
        override val enabled: Boolean = BuildConfig.DEBUG
        override val logger: MyLogger = if (enabled) MyLogger.Debug else MyLogger.Default
    }
}