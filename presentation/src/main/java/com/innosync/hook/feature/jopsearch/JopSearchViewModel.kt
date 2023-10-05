package com.innosync.hook.feature.jopsearch

import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.jopoffer.JopOfferViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JopSearchViewModel @Inject constructor(

): BaseViewModel() {
    fun onClick(view: View) {

    }

    fun onClickMakeBtn() =
        viewEvent(ON_CLICK_MAKE_BTN)

    companion object {
        const val ON_CLICK_MAKE_BTN = 4
    }

}