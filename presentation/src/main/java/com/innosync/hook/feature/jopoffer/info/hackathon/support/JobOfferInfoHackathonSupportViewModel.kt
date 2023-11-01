package com.innosync.hook.feature.jopoffer.info.hackathon.support

import com.innosync.domain.usecase.jobopening.JobOpeningSupportHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class JobOfferInfoHackathonSupportViewModel @Inject constructor(
    private val supportHackathonUseCase: JobOpeningSupportHackathonUseCase
): BaseViewModel() {

    private val _touchState = MutableStateFlow(false)
    val touchState = _touchState.asStateFlow()


    fun createHackathon(
        id: Int,
        name: String,
        contact: String,
        introduction: String,
        portfolioLink: String?
    ) = launchIO {
        supportHackathonUseCase.invoke(
            id = id,
            name = name,
            contact = contact,
            introduction = introduction,
            portfolioLink = portfolioLink
        ).onSuccess {
            notTouch()
            launchMain {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            launchMain {
                notTouch()
                if (it.message == "Fail") {
                    viewEvent(ON_FAILED_OVERLAPPING)
                    return@launchMain
                }
                viewEvent(ON_FAILED)
            }
        }
    }

    fun touch() {
        _touchState.value = true
    }

    fun notTouch() {
        _touchState.value = false
    }

    fun onClickComplete() {
        if (!touchState.value) {
            touch()
            viewEvent(ON_CLICK_COMPLETE)
        }
    }

    fun onClickBack() {
        viewEvent(ON_CLICK_BACK)
    }

    companion object {
        const val ON_CLICK_COMPLETE = 0
        const val ON_CLICK_BACK = 1
        const val ON_SUCCESS = 100
        const val ON_FAILED = 101
        const val ON_FAILED_OVERLAPPING = 102
    }
}