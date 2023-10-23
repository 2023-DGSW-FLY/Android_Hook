package com.innosync.hook.feature.joboffermake.exercise

import com.innosync.domain.usecase.jobopening.JobOpeningInsertExerciseUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class JobOfferMakeExerciseViewModel @Inject constructor(
    private val jobOpeningInsertExerciseUseCase: JobOpeningInsertExerciseUseCase
): BaseViewModel() {

    private val _completeState = MutableStateFlow<Boolean>(false)

    fun createExercise(
        title: String,
        content: String,
        place: String,
        exercise: String,
        dateTime: String,
    ) = launchIO {
        jobOpeningInsertExerciseUseCase.invoke(
            title = title,
            content = content,
            place = place,
            dateTime = dateTime,
            exercise = exercise
        ).onSuccess {
            launchMain {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            _completeState.value = false
            launchMain {
                viewEvent(ON_FAILED)
            }
        }
    }

    fun onClickComplete() {
        if(_completeState.value.not()) {
            _completeState.value = true
            viewEvent(JobOfferMakeViewModel.ON_CLICK_COMPLETE)
        }
    }

    fun onClickBack() =
        viewEvent(ON_CLICK_BACK)

    companion object {
        const val ON_CLICK_COMPLETE = 0
        const val ON_CLICK_BACK = 2
        const val ON_SUCCESS = 100
        const val ON_FAILED = 101
    }
}