package com.innosync.hook.feature.joboffermake

import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class JobOfferMakeViewModel @Inject constructor(

):BaseViewModel() {

    fun onClickComplete() =
        viewEvent(ON_CLICK_COMPLETE)
    fun onClickBack() =
        viewEvent(ON_CLICK_BACK)


    companion object {
        const val ON_CLICK_COMPLETE = 0
        const val ON_CLICK_BACK = 1
    }
}