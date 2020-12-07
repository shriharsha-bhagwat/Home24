package com.home24.assignment.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.home24.assignment.presentation.core.base.BaseViewModel
import com.home24.assignment.presentation.utils.Event

class SplashViewModel() : BaseViewModel() {

    private val _onStartEvent = MutableLiveData<Event<Unit>>()
    val onStartEvent: LiveData<Event<Unit>>
        get() = _onStartEvent

    fun onStartButtonPress() {
        _onStartEvent.value = Event(Unit)
    }
}

