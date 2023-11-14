package com.innosync.hook.feature.jopsearch

import com.innosync.domain.usecase.jobsearch.JobSearchGetStackUseCase
import com.innosync.domain.usecase.jobsearch.JobSearchGetUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JobSearchViewModel @Inject constructor(
    private val jobSearchGetUseCase: JobSearchGetUseCase,
    private val jobSearchGetStackUseCase: JobSearchGetStackUseCase
): BaseViewModel() {

    private val _rvData = MutableStateFlow<List<JobSearchRvModel>>(emptyList())
    val rvData = _rvData.asStateFlow()

    private val _nowButtonData =  MutableStateFlow("")
    val nowButtonData = _nowButtonData.asStateFlow()

    fun loadData() =
        launchIO {
            jobSearchGetUseCase.invoke(

            ).onSuccess {
                _rvData.value = it.toRvModels()
            }.onFailures {

            }
        }
    fun loadStackData(
        category: String
    ) = launchIO {
        jobSearchGetStackUseCase.invoke(
            category
        ).onSuccess {
            _rvData.value = it.toRvModels()
        }.onFailures {

        }
    }
    fun onClickAndroidBtn() {
        viewEvent(ON_CLICK_ANDROID)
        changeData("안드로이드")
    }

    fun onClickServerBtn() {
        viewEvent(ON_CLICK_SERVER)
        changeData("서버")
    }

    fun onClickGameBtn() {
        viewEvent(ON_CLICK_GAME)
        changeData("게임")
    }

    fun onClickMakeBtn() =
        viewEvent(ON_CLICK_MAKE_BTN)

    fun onClickBackBtn() =
        viewEvent(ON_CLICK_BACK_BTN)

    fun onClickEmbeddedBtn() {
        viewEvent(ON_CLICK_EMBEDDED)
        changeData("임베디드")
    }

    fun onClickEtcBtn() {
        viewEvent(ON_CLICK_ETC)
        changeData("기타")
    }

    private fun changeData(stack: String) {
        if (_nowButtonData.value == stack) {
            _nowButtonData.value = ""
        } else {
            _nowButtonData.value = stack
        }
    }

    companion object {
        const val ON_CLICK_ANDROID = 1
        const val ON_CLICK_SERVER = 2
        const val ON_CLICK_GAME = 3
        const val ON_CLICK_EMBEDDED = 4
        const val ON_CLICK_ETC = 5
        const val ON_CLICK_MAKE_BTN = 10
        const val ON_CLICK_BACK_BTN = 11
    }

}