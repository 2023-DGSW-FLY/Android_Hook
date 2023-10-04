package com.innosync.hook.feature.jopoffer

import android.util.Log
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JopOfferViewModel @Inject constructor(

): BaseViewModel() {

    fun onClickCompetitionCategory() {
        Log.d("TAG", "onClickCompetitionCategory: qweqweqwe")
        viewEvent(ON_CLICK_COMPETITION)
    }
    fun onClickFoodCategory() =
        viewEvent(ON_CLICK_FOOD)
    fun onClickHealthCategory() =
        viewEvent(ON_CLICK_HEALTH)
    fun onClickMakePost() =
        viewEvent(ON_CLICK_MAKE_BTN)
    companion object {
        const val ON_CLICK_COMPETITION = 1
        const val ON_CLICK_FOOD = 2
        const val ON_CLICK_HEALTH = 3
        const val ON_CLICK_MAKE_BTN = 4

    }

}