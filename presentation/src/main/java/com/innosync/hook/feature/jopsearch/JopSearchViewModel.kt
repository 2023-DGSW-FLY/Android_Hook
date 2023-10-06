package com.innosync.hook.feature.jopsearch

import com.innosync.domain.usecase.JobSearchGetStackUseCase
import com.innosync.domain.usecase.JobSearchGetUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_BACK_BTN
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_MAKE_BTN
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JopSearchViewModel @Inject constructor(
    private val jobSearchGetUseCase: JobSearchGetUseCase,
    private val jobSearchGetStackUseCase: JobSearchGetStackUseCase
): BaseViewModel() {

    private val _rvData = MutableStateFlow<List<JobSearchRvModel>>(emptyList())
    val rvData = _rvData.asStateFlow()

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
        loadStackData("안드로이드")
    }

    fun onClickServerBtn() {
        viewEvent(ON_CLICK_SERVER)
        loadStackData("서버")
    }

    fun onClickGameBtn() {
        viewEvent(ON_CLICK_GAME)
        loadStackData("게임")
    }

    fun onClickMakeBtn() =
        viewEvent(ON_CLICK_MAKE_BTN)

    fun onClickBackBtn() =
        viewEvent(ON_CLICK_BACK_BTN)

    fun onClickEmbeddedBtn() {
        viewEvent(ON_CLICK_EMBEDDED)
        loadStackData("임베디드")
    }

    fun onClickEtcBtn() {
        viewEvent(ON_CLICK_ETC)
        loadStackData("기타")
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