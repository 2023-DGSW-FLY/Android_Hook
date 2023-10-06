package com.innosync.hook.feature.jopsearch

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJopSearchBinding
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_ANDROID
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_BACK_BTN
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_EMBEDDED
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_ETC
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_GAME
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_MAKE_BTN
import com.innosync.hook.feature.jopsearch.JopSearchViewModel.Companion.ON_CLICK_SERVER
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JopSearchFragment: BaseFragment<FragmentJopSearchBinding, JopSearchViewModel>() {
    override val viewModel: JopSearchViewModel by viewModels()
    val mData = mutableListOf<JobSearchRvModel>()

    val TAG : String = "태그"

    override fun observerViewModel() {
        viewModel.loadData()
        observeState()
        bindingViewEvent { event ->
            when(event){
                ON_CLICK_MAKE_BTN -> {
                    requireContext().shortToast("아직 미구현 기능입니다.")
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
        mBinding.jobSearchRv.addItemDecoration(ItemSpacingDecoration(7))
    }

    private fun observeState() {
        collectLatestStateFlow(viewModel.rvData) {
            val adaptor = JobSearchAdapter(it)
            mBinding.jobSearchRv.adapter = adaptor
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.jobSearchRv.layoutManager = LinearLayoutManager(requireContext())
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
            androidBtn.setBackgroundResource(if (button == "안드로이드") R.drawable.ic_android_on else R.drawable.ic_android_off )
            serverBtn.setBackgroundResource(if (button == "서버") R.drawable.ic_server_on else R.drawable.ic_server_off )
            gameBtn.setBackgroundResource(if (button == "게임") R.drawable.ic_game_on else R.drawable.ic_game_off )
            etcBtn.setBackgroundResource(if (button == "기타") R.drawable.ic_etc_on else R.drawable.ic_etc_off )
            embeddedBtn.setBackgroundResource(if (button == "임베디드") R.drawable.ic_embedded_on else R.drawable.ic_embedded_off )
        }
    }

}