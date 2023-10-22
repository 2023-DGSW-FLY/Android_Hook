package com.innosync.hook.feature.jopsearch.jobsearchmake

import com.innosync.domain.usecase.jobsearch.JobSearchInsertUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JobSearchMakeViewModel @Inject constructor(
    private val jobSearchInsertUseCase: JobSearchInsertUseCase
): BaseViewModel() {

    private val _categoryData = MutableStateFlow("")
    val categoryData = _categoryData.asStateFlow()

    fun insertJobSearch(
        stack: String,
        content: String,
        url: String
    ) = launchIO {
        jobSearchInsertUseCase.invoke(
            stack = stack,
            content = content,
            url = url
        ).onSuccess {
            launchMain {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            launchMain {
                viewEvent(ON_FAILED)
            }
        }
    }

    fun onClickAndroidBtn() {
        viewEvent(ON_CLICK_ANDROID)
        _categoryData.value = "안드로이드"
    }

    fun onClickServerBtn() {
        viewEvent(ON_CLICK_SERVER)
        _categoryData.value = "서버"
    }

    fun onClickGameBtn() {
        viewEvent(ON_CLICK_GAME)
        _categoryData.value = "게임"
    }

    fun onClickMakeBtn() =
        viewEvent(ON_CLICK_MAKE_BTN)


    fun onClickBackBtn() =
        viewEvent(ON_CLICK_BACK_BTN)

    fun onClickEmbeddedBtn() {
        viewEvent(ON_CLICK_EMBEDDED)
        _categoryData.value = "임베디드"
    }

    fun onClickEtcBtn() {
        viewEvent(ON_CLICK_ETC)
        _categoryData.value = "기타"
    }

    companion object {
        const val ON_CLICK_ANDROID = 1
        const val ON_CLICK_SERVER = 2
        const val ON_CLICK_GAME = 3
        const val ON_CLICK_EMBEDDED = 4
        const val ON_CLICK_ETC = 5
        const val ON_CLICK_MAKE_BTN = 10
        const val ON_CLICK_BACK_BTN = 11

        const val ON_SUCCESS = 100
        const val ON_FAILED = 101
    }
}