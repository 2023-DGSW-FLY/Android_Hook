package com.innosync.hook.feature.mybox.detailInfo

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.usecase.ApplicantUseCase
import com.innosync.domain.usecase.jobopening.JobOpeningGetOneHackathonUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailnfoViewModel @Inject constructor(
    private val applicantUseCase: ApplicantUseCase,
    private val myPost: JobOpeningGetOneHackathonUseCase
): BaseViewModel() {



    private val _visibility = MutableLiveData<Int>(View.GONE)
    val visibility: LiveData<Int> = _visibility






}