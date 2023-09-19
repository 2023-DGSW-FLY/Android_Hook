package com.innosync.hook.feature.jopoffer

import com.innosync.hook.base.BaseViewModel

class JopOfferViewModel: BaseViewModel() {

    fun onClickCompetitionCategory() =
        viewEvent(ON_CLICK_COMPETITION)
    fun onClickFoodCategory() =
        viewEvent(ON_CLICK_FOOD)
    fun onClickHealthCategory() =
        viewEvent(ON_CLICK_HEALTH)

    companion object {
        const val ON_CLICK_COMPETITION = 1
        const val ON_CLICK_FOOD = 2
        const val ON_CLICK_HEALTH = 3


    }
}