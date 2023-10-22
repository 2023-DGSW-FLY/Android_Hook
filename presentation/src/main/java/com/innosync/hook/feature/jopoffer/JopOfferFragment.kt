package com.innosync.hook.feature.jopoffer

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.MainActivity
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferBinding
import com.innosync.hook.feature.chat.ChatFragmentDirections
import com.innosync.hook.feature.joboffermake.JobOfferMakeFragmentDirections
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_BACK_BTN
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_COMPETITION
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_FOOD
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_HEALTH
import com.innosync.hook.feature.jopoffer.JopOfferViewModel.Companion.ON_CLICK_MAKE_BTN
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.getYour
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JopOfferFragment :BaseFragment<FragmentJobOfferBinding, JopOfferViewModel>(){

    override val viewModel: JopOfferViewModel by viewModels()

    val TAG : String = "로그"


    override fun observerViewModel() {
        Log.d(TAG, "observerViewModel: qweqweqweqwe")
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPETITION ->{
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(0)
                    visibleText()
                    setTitleText("새로운 도전으로\n달려 보시겠어요?")
                }
                ON_CLICK_FOOD -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(1)
                    invisibleText()
                    setTitleText("맛있는건 항상\n입에 달고 살아야죠!")
                }
                ON_CLICK_HEALTH -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    changeColor(2)
                    invisibleText()
                    setTitleText("당신을 위한\n건강한 한 걸음!")
                }
                ON_CLICK_MAKE_BTN -> {
                    Log.d(TAG, "changeFragment: 버튼클릭")
                    when(viewModel.nowView.value) {
                        "대회" -> {
                            findNavController().navigate(R.id.actionJobOfferFragmentToJobOfferMakeFragment)
                        }
                        "운동" -> {
                            findNavController().navigate(R.id.action_jopOfferFragment_to_jobOfferMakeExerciseFragment)
                        }
                        "음식" -> {
                            findNavController().navigate(R.id.action_jopOfferFragment_to_jobOfferMakeFoodFragment)
                        }
                    }
                }

                ON_CLICK_BACK_BTN -> {
                    findNavController().popBackStack()
                }
            }
        }
//        addData()
//        mBinding.jobOfferRecyclerview.adapter = JopOfferAdapter(mData)

        // 뷰 모델에서 실행된 이벤트를 땡겨씀

    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).moveHome()
        observeState()
        initRv()
        when(viewModel.nowView.value) {
            "대회" -> {
                viewModel.onClickCompetitionCategory()
            }
            "음식" -> {
                viewModel.onClickFoodCategory()
            }
            "운동" -> {
                viewModel.onClickHealthCategory()
            }
        }
    }

    private fun observeState() {
        collectLatestStateFlow(viewModel.rvData) {
            mBinding.jobOfferRecyclerview.adapter = JopOfferAdapter(it) { result ->
                val navigate = when(viewModel.nowView.value) {
                    "대회" -> {
                        JopOfferFragmentDirections.actionJopOfferFragmentToJobOfferInfoHackathonFragment(
                            result.id
                        )
                    }
                    "운동" -> {
                        JopOfferFragmentDirections.actionJopOfferFragmentToJobOfferInfoExerciseFragment(
                            result.id
                        )
                    }

                    else -> {
                        JopOfferFragmentDirections.actionJopOfferFragmentToJobOfferInfoFoodFragment(
                            result.id
                        )
                    }
                }
//                val navigate = ChatFragmentDirections.actionNavItemMessageToMessageFragment(
//                    data.chatRoomUid, data.roomName, my, data.getYour(my)
//                )
                findNavController().navigate(navigate)
            }
        }
    }

    private fun initRv() {
        mBinding.jobOfferRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        mBinding.jobOfferRecyclerview.addItemDecoration(ItemSpacingDecoration(10))
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

    private fun setTitleText(content: String) {
        mBinding.detailText.text = content
    }

//    fun addData(){
//        for (i in 1..100){
//            mData.add(
//                JobOfferModel(
//                    "한준혁",
//                    "해커톤",
//                    "안드로이드",
//                    "11:11",
//                    R.drawable.ic_launcher_foreground
//                )
//             )
//        }
//    }

    fun changeFragment(){
        findNavController().navigate(
            R.id.actionJobOfferFragmentToJobOfferMakeFragment
        )
    }
}