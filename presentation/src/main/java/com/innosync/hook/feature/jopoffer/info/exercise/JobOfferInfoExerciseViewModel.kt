package com.innosync.hook.feature.jopoffer.info.exercise

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.usecase.JobOpeningGetOneExerciseUseCase
import com.innosync.domain.usecase.JobOpeningGetOneHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class JobOfferInfoExerciseViewModel @Inject constructor(
    private val getOneExerciseUseCase: JobOpeningGetOneExerciseUseCase
): BaseViewModel() {

    private val _exerciseInfoState = MutableLiveData<ExerciseModel>()
    val exerciseInfoState: LiveData<ExerciseModel> = _exerciseInfoState

    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility


    fun loadInfo(id: Int) {
        launchIO {
            getOneExerciseUseCase.invoke(
                id
            ).onSuccess { result ->
                launchMain {
                    _exerciseInfoState.value = result
                    _visibility.value = View.VISIBLE
                }
            }.onFailures {
                Log.d("TAG", "loadInfo: $it")
            }
        }
    }
}