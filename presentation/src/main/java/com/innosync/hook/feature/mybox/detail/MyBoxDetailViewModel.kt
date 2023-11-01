package com.innosync.hook.feature.mybox.detail

import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.HackathonModel
import com.innosync.domain.usecase.ApplicantUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetOneHackathonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.innosync.hook.util.launchMain

@HiltViewModel
class MyBoxDetailViewModel @Inject constructor(
    private val applicantUseCase: ApplicantUseCase,
    private val myPost: JobOpeningGetOneHackathonUseCase
): BaseViewModel() {

    private val _myPostData = MutableLiveData<HackathonModel>()
    val myPostData: LiveData<HackathonModel> = _myPostData

    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility

    fun myPostDataLoadInfo(id: Int) = launchIO {
        myPost.invoke(
            id
        ).onSuccess { result ->
            launchMain {
                _myPostData.value = result
                _visibility.value = View.VISIBLE
            }
        }.onFailures {
            Log.d("TAG", "loadInfo: $it")
        }
    }



    private val _rvData = MutableStateFlow<List<ApplicantsRvModel>>(emptyList())
    val rvData = _rvData.asStateFlow()


    fun loadData(
        id: Int
    ) = launchIO{
        applicantUseCase.invoke(
            id = id
        ).onSuccess { result ->
            _rvData.value = result.toRvMyBoxModels()
        }.onFailures {

        }
    }


}