package com.home24.assignment.data.core.base

import com.home24.assignment.domain.core.ResultState
import com.home24.assignment.domain.core.SimpleResult
import com.home24.assignment.presentation.utils.logError
import com.home24.assignment.presentation.utils.logWarn

open class BaseDataSource {

    protected val TAG = "mylogs_${javaClass.simpleName}"

    suspend fun <T> safeCall(tag: String = TAG, call: suspend () -> T): SimpleResult<T> =
        try {
            val result = call.invoke()
            if (result != null) ResultState.Success(result)
            else {
                logWarn(tag, "Safe call returns null")
                ResultState.Failure(NullPointerException("Data is null"))
            }
        } catch (t: Throwable) {
            logError(tag, "${t.message}")
            ResultState.Failure(t)
        }
}