package com.innosync.hook.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.innosync.domain.usecase.CongressUseCase
import com.innosync.domain.usecase.JobOpeningGetEatUseCase
import com.innosync.domain.usecase.JobOpeningGetExerciseUseCase
import com.innosync.domain.usecase.JobOpeningGetHackathonUseCase
import com.innosync.domain.usecase.JobSearchGetRepository
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val congressUseCase: CongressUseCase,
    private val hackathonUseCase: JobOpeningGetHackathonUseCase,
    private val eatUseCase: JobOpeningGetEatUseCase,
    private val exerciseUseCase: JobOpeningGetExerciseUseCase,
    private val jobSearchGetRepository: JobSearchGetRepository
): BaseViewModel() {


    private val _jobRvData = MutableStateFlow(listOf<HomeRvData>())
    val jobRvData = _jobRvData.asStateFlow()

    private val _jobSearchRvData = MutableStateFlow(listOf<HomeRvData>())
    val jobSearchRvData = _jobSearchRvData.asStateFlow()

    private val _congress = MutableStateFlow(listOf<String>())
    val congress = _congress.asStateFlow()


    fun loadCongress() = launchIO {
        congressUseCase.invoke().onSuccess { result ->
            launchMain {
                _congress.value = result.map { it.imgUrl }
            }
        }.onFailure {

        }
    }

    fun onClickHackathon() {
        viewEvent(ON_CLICK_HACKATHON)
        launchIO {
            hackathonUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailure {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickEat() {
        viewEvent(ON_CLICK_EAT)
        launchIO {
            eatUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailure {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickExercise() {
        viewEvent(ON_CLICK_EXERCISE)
        launchIO {
            exerciseUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailure {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickJobOpening() = viewEvent(ON_CLICK_JOB_OPENING)

    fun onClickJobSearch() {
        viewEvent(ON_CLICK_JOB_SEARCH)
        launchIO {
            jobSearchGetRepository.invoke(5).onSuccess {
                Log.d(TAG, "onClickJobSearch: $it")
                launchMain {
                    _jobSearchRvData.value = it.toHomeRvModels()
                    viewEvent(ON_CHANGE_JOB_OPENING_DATA)
                }
            }.onFailure {
                Log.d(TAG, "onClickJobSearch: ${it.message}")
            }
        }
    }

    fun onClickAlarm() = viewEvent(ON_CLICK_ALARM)

    fun onClickSeeAll() = viewEvent(ON_CLICK_SEE_ALL)

    companion object {
        const val ON_CLICK_HACKATHON = 0
        const val ON_CLICK_EAT = 1
        const val ON_CLICK_EXERCISE = 2
        const val ON_CLICK_JOB_OPENING = 3
        const val ON_CLICK_JOB_SEARCH = 4
        const val ON_CLICK_ALARM = 5
        const val ON_CLICK_SEE_ALL = 6

        const val ON_CHANGE_JOB_OPENING_DATA = 100
    }


}