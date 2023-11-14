package com.innosync.hook.feature.jopsearch

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobSearchBinding
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_ANDROID
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_BACK_BTN
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_EMBEDDED
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_ETC
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_GAME
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_MAKE_BTN
import com.innosync.hook.feature.jopsearch.JobSearchViewModel.Companion.ON_CLICK_SERVER
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.removeItemDecorations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobSearchFragment: BaseFragment<FragmentJobSearchBinding, JobSearchViewModel>() {
    override val viewModel: JobSearchViewModel by viewModels()
    val mData = mutableListOf<JobSearchRvModel>()

    val TAG : String = "태그"

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event){
                ON_CLICK_MAKE_BTN -> {
                    findNavController().navigate(
                        R.id.action_jopSearchFragment_to_jobSearchMakeFragment
                    )
                }
                ON_CLICK_BACK_BTN -> {
                    findNavController().popBackStack()
                }
                ON_CLICK_ANDROID -> {
                    setButtonColor("안드로이드")
                }
                ON_CLICK_SERVER -> {
                    setButtonColor("서버")
                }
                ON_CLICK_GAME -> {
                    setButtonColor("게임")
                }
                ON_CLICK_EMBEDDED -> {
                    setButtonColor("임베디드")
                }
                ON_CLICK_ETC -> {
                    setButtonColor("기타")
                }

            }
        }
//        mBinding.jobSearchRv.adapter = JobSearchAdapter(mData)
    }

    private fun observeState() {
        collectLatestStateFlow(viewModel.rvData) {
            val adaptor = JobSearchAdapter(it, requireContext()) { result ->
                val navigate = JobSearchFragmentDirections.actionJopSearchFragmentToJobSearchInfoFragment(
                    result.id
                )
                findNavController().navigate(navigate)
            }
            mBinding.jobSearchRv.adapter = adaptor
        }
        collectLatestStateFlow(viewModel.nowButtonData) {
            if (it == "") {
                viewModel.loadData()
            } else {
                viewModel.loadStackData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        observeState()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
        mBinding.jobSearchRv.layoutManager = LinearLayoutManager(requireContext())
        mBinding.jobSearchRv.removeItemDecorations()
        mBinding.jobSearchRv.addItemDecoration(ItemSpacingDecoration(7))
        viewModel.loadData()
    }

//    fun addData(){
//        for (i in 1..100){
//            mData.add(
//                JobSearchRvModel(
//                    "한준혁",
//                    "11:11",
//                    "안드로이드 잘함"
//                )
//            )
//        }
//    }

    private fun setButtonColor(button: String) {
        with(mBinding) {
            val nowButton = viewModel.nowButtonData.value
            androidBtn.setBackgroundResource(if (button == "안드로이드" && nowButton != button) R.drawable.ic_android_on else R.drawable.ic_android_off )
            serverBtn.setBackgroundResource(if (button == "서버"&& nowButton != button) R.drawable.ic_server_on else R.drawable.ic_server_off )
            gameBtn.setBackgroundResource(if (button == "게임"&& nowButton != button) R.drawable.ic_game_on else R.drawable.ic_game_off )
            etcBtn.setBackgroundResource(if (button == "기타"&& nowButton != button) R.drawable.ic_etc_on else R.drawable.ic_etc_off )
            embeddedBtn.setBackgroundResource(if (button == "임베디드"&& nowButton != button) R.drawable.ic_embedded_on else R.drawable.ic_embedded_off )
        }
    }

}