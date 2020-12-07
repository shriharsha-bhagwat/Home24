package com.home24.assignment.presentation.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home24.assignment.presentation.utils.Event
import com.home24.assignment.presentation.utils.logDebug


/**
 * Generic class for viewModels
 */

abstract class BaseViewModel : ViewModel() {

    protected val _onErrorEvent: MutableLiveData<Event<Any>> = MutableLiveData()
    val onErrorEvent: LiveData<Event<Any>> = _onErrorEvent

    protected val _onMessageEvent: MutableLiveData<Event<Any>> = MutableLiveData()
    val onMessageEvent: LiveData<Event<Any>> = _onMessageEvent

    protected val _onFinishActivityEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val onFinishActivityEvent: LiveData<Event<Unit>> = _onFinishActivityEvent

    override fun onCleared() {
        logDebug(message = "${javaClass.simpleName} on cleared called")
        super.onCleared()
    }
}