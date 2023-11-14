package com.innosync.hook.feature.mybox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.ExerciseModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.model.JobSearchModel
import com.innosync.domain.usecase.mybox.MyBoxCompleteUseCase
import com.innosync.domain.usecase.mybox.MyBoxEatGetUseCase
import com.innosync.domain.usecase.mybox.MyBoxExerciseUseCase
import com.innosync.domain.usecase.mybox.MyBoxHackathonGetUseCase
import com.innosync.domain.usecase.mybox.MyBoxJobSearchGetUseCase
import com.innosync.domain.usecase.mybox.MyBoxMatchingUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.home.HomeViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyBoxViewModel @Inject constructor(
    private val myBoxHackathonGetUseCase: MyBoxHackathonGetUseCase,
    private val myBoxEatGetUseCase: MyBoxEatGetUseCase,
    private val myBoxExerciseUseCase: MyBoxExerciseUseCase,
    private val myBoxJobSearchGetUseCase: MyBoxJobSearchGetUseCase,
    private val myBoxCompleteUseCase: MyBoxCompleteUseCase,
    private val myBoxMatchingUseCase: MyBoxMatchingUseCase
) : BaseViewModel() {
    //viewmodel을 사용해서 아이템을 눌렀을 때
    private val _rvData = MutableLiveData<List<MyBoxRvData>>()
    val rvData: LiveData<List<MyBoxRvData>> = _rvData

    fun complete(
        id: Int,
        type: String
    ) = launchIO {
        myBoxCompleteUseCase.invoke(
            id =  id,
            type = type
        ).onSuccess {
            //다시 로드 값을 다시 가져오기
            when (type) {
                "exercise" -> {
                    loadExercise()
                }
                "food" -> {
                    loadEat()
                }
                "hackathon" -> {
                    loadHackathon()
                }
                "access" -> {
                    loadJobSearch()
                }
            }
        }.onFailures {

        }
    }

    fun match(
        id: Int,
        type: String
    ) = launchIO {
        myBoxMatchingUseCase.invoke(
            id =  id,
            type = type
        ).onSuccess {
            when (type) {
                "exercise" -> {
                    loadExercise()
                }
                "food" -> {
                    loadEat()
                }
                "hackathon" -> {
                    loadHackathon()
                }
                "JobSearchModel" -> {
                    loadJobSearch()
                }
            }
        }.onFailures {

        }
    }

    fun loadHackathon(
    ) {
        launchMain {
            viewEvent(ON_CLICK_HACKATHON)
        }
        viewModelScope.launch(Dispatchers.IO) {
            myBoxHackathonGetUseCase.invoke(

            ).onSuccess {
                launchMain {
                    _rvData.value = it.toMyBoxRvDatas()
                }
            }.onFailures {

            }
        }
    }

    fun loadEat(
    ) {
        launchMain {
            viewEvent(ON_CLICK_EAT)
        }
        viewModelScope.launch(Dispatchers.IO) {
            myBoxEatGetUseCase.invoke(

            ).onSuccess {
                launchMain {
                    _rvData.value = it.toMyBoxRvDatas()
                }
            }.onFailures {

            }
        }
    }

    fun addstatus() {

    }

    fun loadExercise(
    ) {
        launchMain {
            viewEvent(ON_CLICK_EXERCISE)
        }
        viewModelScope.launch(Dispatchers.IO) {
            myBoxExerciseUseCase.invoke(

            ).onSuccess {
                launchMain {
                    _rvData.value = it.toMyBoxRvDatas()
                }
            }.onFailures {

            }
        }
    }

    fun loadJobSearch(
    ) {
        launchMain {
            viewEvent(ON_CLICK_COLLECT_PEOPLE)
        }
        viewModelScope.launch(Dispatchers.IO) {
            myBoxJobSearchGetUseCase.invoke(

            ).onSuccess {
                launchMain {
                    _rvData.value = it.toMyBoxRvDatas()
                }
            }.onFailures {

            }
        }
    }

    companion object {
        const val ON_CLICK_HACKATHON = 0
        const val ON_CLICK_EAT = 1
        const val ON_CLICK_EXERCISE = 2
        const val ON_CLICK_COLLECT_PEOPLE = 3
    }
}

internal fun List<Any>.toMyBoxRvDatas() = this.mapNotNull { item ->
    when (item) {
        is ExerciseModel -> item.toMyBoxRvData()
        is EatModel -> item.toMyBoxRvData()
        is HackathonModel -> item.toMyBoxRvData()
        is JobSearchModel -> item.toMyBoxRvData()
        else -> null
    }
}


//텍스트는 어댑터에서.

