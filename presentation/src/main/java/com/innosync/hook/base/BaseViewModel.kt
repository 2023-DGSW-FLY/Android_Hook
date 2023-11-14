package com.innosync.hook.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.innosync.hook.util.launchMain
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel: ViewModel() {
    private val _viewEvent = MutableLiveData<Any>()
    val viewEvent: LiveData<Any>
        get() = _viewEvent

    val tokenErrorEvent = MutableLiveData<String>()

    fun viewEvent(content: Any) {
        _viewEvent.value = content
    }
    fun <T> Result<T>.onFailures(
        action: (Throwable) -> Unit
    ): Result<T> {
        this.onFailure {
            Log.d("TAG", "onFailures: ${it.message}")
            if (it.message == "HTTP 403 세션이 만료되었습니다.") {
                launchMain {
                    tokenErrorEvent.value = "token_exception"
                }
                return@onFailure
            }
            action(it)
        }
        return this
    }
//    fun CoroutineScope.Error(message: String) {
//        Log.d("TAG", "Error: ${tokenErrorEvent.value}")
//    }
}