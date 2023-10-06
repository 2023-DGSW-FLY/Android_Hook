package com.innosync.hook.feature.jopoffer

import android.util.Log
import com.innosync.domain.usecase.JobOpeningGetEatUseCase
import com.innosync.domain.usecase.JobOpeningGetExerciseUseCase
import com.innosync.domain.usecase.JobOpeningGetHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.home.toHomeRvModels
import com.innosync.hook.feature.jopoffer.mapper.toJobOfferModels
import com.innosync.hook.feature.jopoffer.mapper.tooJobOfferModels
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JopOfferViewModel @Inject constructor(
    private val hackathonUseCase: JobOpeningGetHackathonUseCase,
    private val eatUseCase: JobOpeningGetEatUseCase,
    private val exerciseUseCase: JobOpeningGetExerciseUseCase
): BaseViewModel() {

    private val _rvData = MutableStateFlow<List<JobOfferModel>>(emptyList())
    val rvData = _rvData.asStateFlow()

    private val _nowView = MutableStateFlow("대회")
    val nowView = _nowView.asStateFlow()

    fun onClickCompetitionCategory() {
        Log.d("TAG", "onClickCompetitionCategory: qweqweqwe")
        _nowView.value = "대회"
        viewEvent(ON_CLICK_COMPETITION)
        launchIO {
            hackathonUseCase.invoke().onSuccess {
                _rvData.value = it.toJobOfferModels()
            }.onFailures {
                Log.d("TAG", "onClickCompetitionCategory: $it")
            }
        }
    }
    fun onClickFoodCategory() {
        _nowView.value = "음식"
        viewEvent(ON_CLICK_FOOD)
        launchIO {
            eatUseCase.invoke().onSuccess {
                _rvData.value = it.tooJobOfferModels()
            }.onFailures {
                Log.d("TAG", "onClickFoodCategory: $it")
            }
        }
    }
    fun onClickHealthCategory() {
        _nowView.value = "운동"
        viewEvent(ON_CLICK_HEALTH)
        launchIO {
            exerciseUseCase.invoke().onSuccess {
                _rvData.value = it.toJobOfferModels()
            }.onFailures {
                Log.d("TAG", "onClickHealthCategory: $it")
            }
        }
    }
    fun onClickMakePost() =
        viewEvent(ON_CLICK_MAKE_BTN)
    fun onClickBackBtn() =
        viewEvent(ON_CLICK_BACK_BTN)
    companion object {
        const val ON_CLICK_COMPETITION = 1
        const val ON_CLICK_FOOD = 2
        const val ON_CLICK_HEALTH = 3
        const val ON_CLICK_MAKE_BTN = 4
        const val ON_CLICK_BACK_BTN = 5

    }

}