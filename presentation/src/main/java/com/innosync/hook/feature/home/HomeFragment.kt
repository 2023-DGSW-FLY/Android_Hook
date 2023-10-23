package com.innosync.hook.feature.home

import android.app.ActionBar
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.innosync.hook.feature.loading.ImageDialog
import com.innosync.hook.util.ItemRightSpacingDecoration
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun observerViewModel() {
        Log.d(TAG, "observerViewModel: on observer")
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
                ON_CLICK_SEE_ALL -> {
                    when(viewModel.nowView.value) {
                        "구직" -> {
                            findNavController().navigate(
                                R.id.action_nav_item_home_to_jopSearchFragment
                            )
                        }
                        else -> {
                            findNavController().navigate(
                                R.id.action_nav_item_home_to_jopOfferFragment
                            )
                        }

                    }
                }
                ON_CLICK_ALARM -> {}
                ON_CLICK_JOB_SEARCH -> {
                    val context = requireContext()
                    mBinding.layoutCategory.visibility = View.INVISIBLE
                    mBinding.textJobSearch.setTextColor(ContextCompat.getColor(context, R.color.black))
                    mBinding.textJobOpening.setTextColor(ContextCompat.getColor(context, R.color.darkGray))
                }
                ON_CLICK_JOB_OPENING -> {
                    val context = requireContext()
                    mBinding.layoutCategory.visibility = View.VISIBLE
                    // 구인은 눌러도 바로 반응이 없기 떄문에 기존 데이터 로드
                    mBinding.rvJob.adapter = HomeRvAdaptor(viewModel.jobRvData.value) {
                        findNavController().navigate(
                            R.id.action_nav_item_home_to_jopOfferFragment
                        )
                    }
                    mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
                    mBinding.textJobSearch.setTextColor(ContextCompat.getColor(context, R.color.darkGray))
                    mBinding.textJobOpening.setTextColor(ContextCompat.getColor(context, R.color.black))
//                    mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
                }

                ON_CHANGE_JOB_OPENING_DATA -> {
                    val adaptor = HomeRvAdaptor(viewModel.jobSearchRvData.value) {
                        findNavController().navigate(
                            R.id.action_nav_item_home_to_jopSearchFragment
                        )
                    }
                    mBinding.rvJob.adapter = adaptor
                    mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initRv()
        observeData()
        viewModel.onClickHackathon()

    }

    private fun initRv() {
        mBinding.rvJob.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvJob.addItemDecoration(ItemSpacingDecoration(8))
    }

    private fun observeData() {
        collectLatestStateFlow(viewModel.jobRvData) {
            Log.d(TAG, "observeData: $it")
            val adaptor = HomeRvAdaptor(it) {
                findNavController().navigate(
                    R.id.action_nav_item_home_to_jopOfferFragment
                )
            }
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
            ) { url ->
                val myDialog = ImageDialog(requireContext(), url)
                myDialog.show()
                myDialog.setCancelable(true)
                val window: Window = myDialog.window!!
                window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT)
            }
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