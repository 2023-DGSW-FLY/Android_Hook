package com.innosync.hook.feature.joboffermake

import android.util.Log
import com.innosync.domain.usecase.jobopening.JobOpeningInsertHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JobOfferMakeViewModel @Inject constructor(
    private val jobOpeningInsertHackathonUseCase: JobOpeningInsertHackathonUseCase
):BaseViewModel() {



    fun onClickComplete() =
        viewEvent(ON_CLICK_COMPLETE)
    fun onClickBack() =
        viewEvent(ON_CLICK_BACK)

    fun insertData(
        title: String,
        content: String,
        stack: List<String>,
        url: String
    ) = launchIO {
        jobOpeningInsertHackathonUseCase.invoke(
            title = title,
            content = content,
            stack = stack,
            url = url
        ).onSuccess {
            launchMain {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            Log.d("TAG", "insertData: $it")
            launchMain {
                viewEvent(ON_FAILED)
            }
        }
    }


    companion object {
        const val ON_CLICK_COMPLETE = 0
        const val ON_CLICK_BACK = 1
        const val ON_SUCCESS = 100
        const val ON_FAILED = 101
    }
}