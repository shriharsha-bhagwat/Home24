package com.home24.assignment.presentation.utils

import com.home24.assignment.core.Home24App
import com.ironz.binaryprefs.Preferences
import com.ironz.binaryprefs.PreferencesEditor


fun logWarn(tag: String = "mylogs", message: String) =
    Home24App.debug.logger.logWarn(tag, message)

fun logWarn(clazz: Class<Any>, message: String) =
    logWarn("mylogs_${clazz.simpleName}", message)


fun logError(tag: String = "mylogs", message: String) =
    Home24App.debug.logger.logError(tag, message)

fun logError(clazz: Class<Any>, message: String) =
    logError("mylogs_${clazz.simpleName}", message)


fun logDebug(tag: String = "mylogs", message: String) =
    Home24App.debug.logger.logDebug(tag, message)

fun logDebug(clazz: Class<Any>, message: String) =
    logDebug("mylogs_${clazz.simpleName}", message)


fun logInfo(tag: String = "mylogs", message: String) =
    Home24App.debug.logger.logInfo(tag, message)

fun logInfo(clazz: Class<Any>, message: String) =
    logInfo("mylogs_${clazz.simpleName}", message)


fun logWtf(tag: String = "mylogs", message: String) =
    Home24App.debug.logger.logWtf(tag, message)

fun logWtf(clazz: Class<Any>, message: String) =
    logWtf("mylogs_${clazz.simpleName}", message)


fun Preferences.commit(block: PreferencesEditor.() -> Unit) {
    val editor = this.edit()
    block(editor)
    editor.commit()
}

fun Preferences.apply(block: PreferencesEditor.() -> Unit) {
    val editor = this.edit()
    block(editor)
    editor.apply()
}
