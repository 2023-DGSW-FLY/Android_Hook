package com.innosync.hook.feature.mybox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.usecase.mybox.MyBoxHackathonGetUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MyBoxViewModel @Inject constructor(
    private val myBoxHackathonGetUseCase: MyBoxHackathonGetUseCase
) : BaseViewModel() {

//    private val _rvData = MutableLiveData<MyboxData>()
//    val rvData: LiveData<MyboxData> = _rvData

    private val _rvData = MutableLiveData<List<MyBoxRvData>>()
    val rvData: LiveData<List<MyBoxRvData>> = _rvData

    fun getHackathon() = launchIO {
        myBoxHackathonGetUseCase.invoke(

        ).onSuccess {
            _rvData.value = it.toMyBoxRvDatas()
        }.onFailures {

        }
    }
}