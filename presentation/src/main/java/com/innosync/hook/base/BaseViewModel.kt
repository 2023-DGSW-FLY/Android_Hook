package com.innosync.hook.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    private val _viewEvent = MutableLiveData<Any>()
    val viewEvent: LiveData<Any>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = content
    }
}