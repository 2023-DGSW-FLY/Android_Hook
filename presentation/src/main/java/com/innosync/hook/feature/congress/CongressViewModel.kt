package com.innosync.hook.feature.congress

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.CongressModel
import com.innosync.domain.usecase.CongressUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CongressViewModel @Inject constructor(
    private val congressUseCase: CongressUseCase
): BaseViewModel() {

    private val _congressData = MutableLiveData<List<CongressModel>>(emptyList())
    val congressData: LiveData<List<CongressModel>> = _congressData

    fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        congressUseCase.invoke().onSuccess {data ->
            launchMain {
                _congressData.value = data
            }
        }.onFailure {
            launchMain {
                viewEvent(ON_ERROR_LOAD)
            }
        }
    }

    private fun launchMain(action: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            action()
        }
    }

    companion object {
        const val ON_ERROR_LOAD = 100
    }
}