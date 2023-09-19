package com.innosync.hook.feature.jopoffer

import android.util.Log
import androidx.fragment.app.viewModels
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJopOfferBinding
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_COMPETITION
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_FOOD
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_HEALTH

class JopOfferFragment :BaseFragment<FragmentJopOfferBinding, JopOfferViewModel>(){
    override val viewModel: JopOfferViewModel by viewModels()
    val TAG : String = "태그"

    override fun observerViewModel() {
        // 뷰 모델에서 실행된 이벤트를 땡겨씀
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPETITION ->{
                    Log.d(TAG, "observerViewModel: 대회")
                    changeColor(0)
                }
                ON_CLICK_FOOD -> {
                    Log.d(TAG, "observerViewModel: 음식")
                    changeColor(1)
                }
                ON_CLICK_HEALTH -> {
                    Log.d(TAG, "observerViewModel: 운동")
                    changeColor(2)
                }
            }
        }
    }
    fun changeColor(
        btn: Int
    ){
        with(mBinding) {
            competitionCategoryBtn.setImageDrawable(getDrawable(if (btn == 0) R.drawable.ic_competition_btn_on else R.drawable.ic_competition_btn_off))
            foodCategoryBtn.setImageDrawable(getDrawable(if (btn == 1) R.drawable.ic_food_btn_on else R.drawable.ic_food_btn_off))
            healthCategoryBtn.setImageDrawable(getDrawable(if (btn == 2) R.drawable.ic_health_btn_on else R.drawable.ic_health_btn_off))
        }
    }

    private fun getDrawable(id: Int) =
        requireContext().getDrawable(id)


}