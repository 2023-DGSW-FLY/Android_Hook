package com.innosync.hook.feature.jopoffer

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.data.repository.model.JobOfferModel
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJopOfferBinding
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_COMPETITION
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_FOOD
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_HEALTH
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_MAKE_BTN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JopOfferFragment :BaseFragment<FragmentJopOfferBinding, JopOfferViewModel>(){

    override val viewModel: JopOfferViewModel by viewModels()
    val mData = mutableListOf<JobOfferModel>()
    val TAG : String = "로그"
  

    override fun observerViewModel() {
        Log.d(TAG, "observerViewModel: qweqweqweqwe")
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPETITION ->{
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(0)
                    visibleText()
                }
                ON_CLICK_FOOD -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(1)
                    invisibleText()
                }
                ON_CLICK_HEALTH -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(2)
                    invisibleText()
                }
                ON_CLICK_MAKE_BTN -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeFragment()
                }
            }
        }
        addData()
        mBinding.jobOfferRecyclerview.adapter = JopOfferAdapter(mData)
        initRv()

        // 뷰 모델에서 실행된 이벤트를 땡겨씀

    }

    private fun initRv() {
        mBinding.jobOfferRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
    private fun changeColor(
        btn: Int
    ){
        with(mBinding) {
            competitionCategoryBtn.setImageDrawable(getDrawable(if (btn == 0) R.drawable.ic_competition_btn_on else R.drawable.ic_competition_btn_off))
            foodCategoryBtn.setImageDrawable(getDrawable(if (btn == 1) R.drawable.ic_food_btn_on else R.drawable.ic_food_btn_off))
            healthCategoryBtn.setImageDrawable(getDrawable(if (btn == 2) R.drawable.ic_health_btn_on else R.drawable.ic_health_btn_off))
        }
    }
    private fun invisibleText(){
        mBinding.CTSTextLine.visibility = View.INVISIBLE
    }
    private fun visibleText(){
        mBinding.CTSTextLine.visibility = View.VISIBLE
    }

    private fun getDrawable(id: Int) =
        requireContext().getDrawable(id)


    fun addData(){
        for (i in 1..100){
            mData.add(
                JobOfferModel(
                    "한준혁",
                    "해커톤",
                    "안드로이드",
                    "11:11",
                    R.drawable.ic_launcher_foreground
                )
             )
        }
    }

    fun changeFragment(){
        findNavController().navigate(
            R.id.actionJobOfferFragmentToJobOfferMakeFragment
        )
    }
}