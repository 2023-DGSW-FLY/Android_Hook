package com.innosync.hook.feature.jopoffer.info.hackathon

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.EatModel
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.usecase.JobOpeningGetOneHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class JobOfferInfoHackathonViewModel @Inject constructor(
    private val hackathonUseCase: JobOpeningGetOneHackathonUseCase
): BaseViewModel() {

    private val _hackathonInfoData = MutableLiveData<HackathonModel>()
    val hackathonInfoData: LiveData<HackathonModel> = _hackathonInfoData

    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility

    fun loadInfo(id: Int) = launchIO {
        hackathonUseCase.invoke(
            id
        ).onSuccess { result ->
            launchMain {
                _hackathonInfoData.value = result
                _visibility.value = View.VISIBLE
            }
        }.onFailures {
            Log.d("TAG", "loadInfo: $it")
        }
    }
    fun onClickSupport() =
        viewEvent(ON_CLICK_SUPPORT)

    companion object {
        const val ON_CLICK_SUPPORT = 0
    }
}