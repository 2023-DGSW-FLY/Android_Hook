package com.innosync.hook.feature.jopsearch.jobsearchmake

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobSearchMakeBinding
import com.innosync.hook.feature.jopsearch.JopSearchViewModel
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_ANDROID
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_BACK_BTN
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_EMBEDDED
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_ETC
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_GAME
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_MAKE_BTN
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_CLICK_SERVER
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_FAILED
import com.innosync.hook.feature.jopsearch.jobsearchmake.JobSearchMakeViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobSearchMakeFragment: BaseFragment<FragmentJobSearchMakeBinding, JobSearchMakeViewModel>() {

    override val viewModel: JobSearchMakeViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {
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
                ON_CLICK_MAKE_BTN -> {
                    val context = requireContext()
                    with(mBinding) {
                        if (editIntro.text.isNullOrBlank()) {
                            context.shortToast("자기소개를 적어주세요.")
                            return@bindingViewEvent
                        }
                        if (competitionLinkEditText.text.isNullOrBlank()) {
                            context.shortToast("포트폴리오 링크를 적어주세요.")
                            return@bindingViewEvent
                        }
                        viewModel.insertJobSearch(
                            stack = viewModel.categoryData.value,
                            content = editIntro.text.toString(),
                            url = competitionLinkEditText.text.toString(),
                        )

                    }
                }
                ON_SUCCESS -> {
                    requireContext().shortToast("등록에 성공하였습니다!")
                    findNavController().popBackStack()
                }
                ON_FAILED -> {
                    requireContext().shortToast("등록에 실패하였습니다")
                }

            }
        }
    }
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