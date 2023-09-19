package com.innosync.hook

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.innosync.domain.usecase.ExampleGetUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val exampleGetUseCase: ExampleGetUseCase
) : BaseViewModel() {

    fun getLog() = viewModelScope.launch(Dispatchers.IO) {
        exampleGetUseCase(
            param = ExampleGetUseCase.Param(
                "밥먹고싶다"
            )
        ).onSuccess {
            Log.d("TAG", "getLog: $it")
        }.onFailure {
            Log.d("TAG", "getLog: $it")
        }
    }

}