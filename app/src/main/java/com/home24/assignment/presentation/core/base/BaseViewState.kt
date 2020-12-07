package com.home24.assignment.presentation.core.base

import com.home24.assignment.presentation.core.ViewState
import com.home24.assignment.presentation.core.base.BaseViewState.*

/**
 * [ViewState] for [BaseViewState]
 * state [Loading] indicates that nothing is really happening and adding process is in queue
 * state [Success] indicates that adding operation was successful
 * state [Error] responsible for indicating errors
 */

sealed class BaseViewState : ViewState {
    object Loading : BaseViewState()
    data class Success(val data: Any) : BaseViewState()
    data class Error(val errorMessage: String?) : BaseViewState()
}