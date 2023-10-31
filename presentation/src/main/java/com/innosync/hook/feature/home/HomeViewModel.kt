package com.innosync.hook.feature.home

import android.util.Log
import com.innosync.domain.model.AlarmModel
import com.innosync.domain.usecase.CongressUseCase
import com.innosync.domain.usecase.alarm.AlarmGetAllUseCase
import com.innosync.domain.usecase.alarm.AlarmGetLastTimeUseCase
import com.innosync.domain.usecase.alarm.AlarmInsertLastCheckUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetEatUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetExerciseUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetHackathonUseCase
import com.innosync.domain.usecase.jobsearch.JobSearchGetUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val congressUseCase: CongressUseCase,
    private val hackathonUseCase: JobOpeningGetHackathonUseCase,
    private val eatUseCase: JobOpeningGetEatUseCase,
    private val exerciseUseCase: JobOpeningGetExerciseUseCase,
    private val jobSearchGetUseCase: JobSearchGetUseCase,
    private val alarmGetAllUseCase: AlarmGetAllUseCase,
    private val alarmGetLastTimeUseCase: AlarmGetLastTimeUseCase,
    private val alarmInsertLastCheckUseCase: AlarmInsertLastCheckUseCase
): BaseViewModel() {


    private val _jobRvData = MutableStateFlow(listOf<HomeRvData>())
    val jobRvData = _jobRvData.asStateFlow()

    private val _jobSearchRvData = MutableStateFlow(listOf<HomeRvData>())
    val jobSearchRvData = _jobSearchRvData.asStateFlow()

    private val _congress = MutableStateFlow(listOf<String>())
    val congress = _congress.asStateFlow()

    private val _nowView = MutableStateFlow("대회")
    val nowView = _nowView.asStateFlow()

    private val _alarm = MutableStateFlow<List<AlarmModel>>(emptyList())
    val alarm = _alarm.asStateFlow()

    private val _newAlarm = MutableStateFlow<Boolean>(false)
    val newAlarm = _newAlarm.asStateFlow()

    fun loadCongress() = launchIO {
        congressUseCase.invoke().onSuccess { result ->
            launchMain {
                _congress.value = result.map { it.imgUrl }
            }
        }.onFailures {

        }
    }

    fun loadAlarm() = launchIO {
        alarmGetAllUseCase.invoke(

        ).onSuccess {
            _alarm.value = it
            if (it.isNotEmpty()) {
                val time = alarmGetLastTimeUseCase.invoke()
                Log.d(TAG, "loadAlarm: 로컬 $time\n${it[0].regDate}")
                _newAlarm.value = time.isBefore(it[0].regDate)
            }
        }.onFailures {

        }
    }

    fun onClickHackathon() {
        _nowView.value = "대회"
        viewEvent(ON_CLICK_BACKGROUND)
        viewEvent(ON_CLICK_HACKATHON)
        launchIO {
            hackathonUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailures {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickEat() {
        _nowView.value = "음식"
        viewEvent(ON_CLICK_BACKGROUND)
        viewEvent(ON_CLICK_EAT)
        launchIO {
            eatUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailures {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickExercise() {
        _nowView.value = "운동"
        viewEvent(ON_CLICK_BACKGROUND)
        viewEvent(ON_CLICK_EXERCISE)
        launchIO {
            exerciseUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickHackathon: $it")
                launchMain {
                    _jobRvData.value = it.toHomeRvModels()
                }
            }.onFailures {
                Log.d(TAG, "onClickHackathon: ${it.message}")
            }
        }
    }

    fun onClickJobOpening() {
        viewEvent(ON_CLICK_BACKGROUND)
        viewEvent(ON_CLICK_JOB_OPENING)
    }

    fun onClickJobSearch() {
        _nowView.value = "구직"
        viewEvent(ON_CLICK_JOB_SEARCH)
        viewEvent(ON_CLICK_BACKGROUND)
        launchIO {
            jobSearchGetUseCase.invoke(5).onSuccess {
                Log.d(TAG, "onClickJobSearch: $it")
                launchMain {
                    _jobSearchRvData.value = it.toHomeRvModels()
                    viewEvent(ON_CHANGE_JOB_OPENING_DATA)
                }
            }.onFailures {
                Log.d(TAG, "onClickJobSearch: ${it.message}")
            }
        }
    }

    fun onClickAlarm() {
        viewEvent(ON_CLICK_ALARM)
        launchIO {
            Log.d(TAG, "onClickAlarm: ${LocalDateTime.now()}")
            alarmInsertLastCheckUseCase.invoke(LocalDateTime.now())
        }
    }

    fun onClickSeeAll() {
        viewEvent(ON_CLICK_BACKGROUND)
        viewEvent(ON_CLICK_SEE_ALL)
    }

    fun onClickBackground() {
        Log.d(TAG, "onClickBackground: click")
        viewEvent(ON_CLICK_BACKGROUND)
    }

    companion object {
        const val ON_CLICK_HACKATHON = 0
        const val ON_CLICK_EAT = 1
        const val ON_CLICK_EXERCISE = 2
        const val ON_CLICK_JOB_OPENING = 3
        const val ON_CLICK_JOB_SEARCH = 4
        const val ON_CLICK_ALARM = 5
        const val ON_CLICK_SEE_ALL = 6
        const val ON_CLICK_BACKGROUND = 7

        const val ON_CHANGE_JOB_OPENING_DATA = 100
    }


}