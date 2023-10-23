package com.innosync.hook.feature.joboffermake.food

import android.util.Log
import com.innosync.domain.usecase.jobopening.JobOpeningInsertEatUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JobOfferMakeFoodViewModel @Inject constructor(
    private val jobOpeningInsertEatUseCase: JobOpeningInsertEatUseCase
): BaseViewModel() {

    private val _checkState = MutableStateFlow(false)
    val checkState = _checkState.asStateFlow()

    private val _completeState = MutableStateFlow<Boolean>(false)

    fun createFood(
        foodName: String?,
        title: String,
        content: String,
        place: String,
    ) = launchIO {
        jobOpeningInsertEatUseCase.invoke(
            foodName = foodName,
            title = title,
            content = content,
            place = place
        ).onSuccess {
            launchMain {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            launchMain {
                _completeState.value = false
                viewEvent(ON_FAILED)
            }
        }
    }

    fun onClickCheckBox() {
        Log.d("TAG", "onClickCheckBox: ${checkState.value}")
        _checkState.value = _checkState.value.not()
        viewEvent(ON_CLICK_CHECKBOX)
    }

    fun onClickComplete() {
        if(_completeState.value.not()) {
            _completeState.value = true
            viewEvent(JobOfferMakeViewModel.ON_CLICK_COMPLETE)
        }
    }

    fun onClickBack() =
        viewEvent(JobOfferMakeViewModel.ON_CLICK_BACK)

    companion object {
        const val ON_CLICK_COMPLETE = 0
        const val ON_CLICK_CHECKBOX = 1
        const val ON_CLICK_BACK = 2
        const val ON_SUCCESS = 100
        const val ON_FAILED = 101
    }
}