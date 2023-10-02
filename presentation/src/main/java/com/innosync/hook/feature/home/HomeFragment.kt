package com.innosync.hook.feature.home

import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentHomeBinding
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CHANGE_JOB_OPENING_DATA
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_ALARM
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_EAT
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_EXERCISE
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_HACKATHON
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_JOB_OPENING
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_JOB_SEARCH
import com.innosync.hook.feature.home.HomeViewModel.Companion.ON_CLICK_SEE_ALL
import com.innosync.hook.util.ItemRightSpacingDecoration
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        initRv()
        viewModel.loadCongress()
        bindingViewEvent {  event ->
            when(event) {
                ON_CLICK_HACKATHON -> {
                    setBtnColor("대회")
                }
                ON_CLICK_EAT -> {
                    setBtnColor("식사")
                }
                ON_CLICK_EXERCISE -> {
                    setBtnColor("운동")
                }
                ON_CLICK_SEE_ALL -> {}
                ON_CLICK_ALARM -> {}
                ON_CLICK_JOB_SEARCH -> {
                    mBinding.layoutCategory.visibility = View.INVISIBLE
                }
                ON_CLICK_JOB_OPENING -> {
                    mBinding.layoutCategory.visibility = View.VISIBLE
                    // 구인은 눌러도 바로 반응이 없기 떄문에 기존 데이터 로드
                    mBinding.rvJob.adapter = HomeRvAdaptor(viewModel.jobRvData.value)
                    mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
//                    mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
                }

                ON_CHANGE_JOB_OPENING_DATA -> {
                    val adaptor = HomeRvAdaptor(viewModel.jobSearchRvData.value)
                    mBinding.rvJob.adapter = adaptor
                    mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
        viewModel.onClickHackathon()
    }

    private fun initRv() {
        mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
    }

    private fun observeData() {
        collectLatestStateFlow(viewModel.jobRvData) {
            Log.d(TAG, "observeData: $it")
            val adaptor = HomeRvAdaptor(it)
            mBinding.rvJob.adapter = adaptor
            mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
//            mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
        }

//        collectLatestStateFlow(viewModel.jobSearchRvData) {
//            Log.d(TAG, "observeData: $it")
//            val adaptor = HomeRvAdaptor(it)
//            mBinding.rvJob.adapter = adaptor
//            mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
////            mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
//        }

        collectLatestStateFlow(viewModel.congress) {
            Log.d(TAG, "observeData: $it")
            val adaptor = HomeCongressRvAdaptor(
                it,
                requireContext()
            ) {}
            mBinding.rvCongressInfo.adapter = adaptor
            mBinding.rvCongressInfo.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            mBinding.rvCongressInfo.addItemDecoration(ItemRightSpacingDecoration(16))
        }
    }

    private fun setBtnColor(btn: String) {
        val darkBackground = context?.getDrawable(R.drawable.shape_dark_gray)!!
        val blueBackground = context?.getDrawable(R.drawable.shape_sky_blue)!!
        val darkText = context?.getColor(R.color.homeTextGray)!!
        val blueText = context?.getColor(R.color.textBlue)!!

        fun getBackground(mBtn: String): Drawable =
            if (btn == mBtn) blueBackground else darkBackground
        fun getTextColor(mBtn: String): Int =
            if (btn == mBtn) blueText else darkText

        with(mBinding) {
            btnHackathon.background = getBackground("대회")
            btnHackathon.setTextColor(getTextColor("대회"))

            btnEat.background = getBackground("식사")
            btnEat.setTextColor(getTextColor("식사"))

            btnExercise.background = getBackground("운동")
            btnExercise.setTextColor(getTextColor("운동"))
        }
    }




    companion object {
        const val TAG = "LOG"
    }
}