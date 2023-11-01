package com.innosync.hook.feature.jopoffer.info.hackathon.support

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferIntoHackathonSupportBinding
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import com.innosync.hook.feature.jopoffer.info.hackathon.JobOfferInfoHackathonFragmentArgs
import com.innosync.hook.feature.jopoffer.info.hackathon.support.JobOfferInfoHackathonSupportViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.jopoffer.info.hackathon.support.JobOfferInfoHackathonSupportViewModel.Companion.ON_CLICK_COMPLETE
import com.innosync.hook.feature.jopoffer.info.hackathon.support.JobOfferInfoHackathonSupportViewModel.Companion.ON_FAILED
import com.innosync.hook.feature.jopoffer.info.hackathon.support.JobOfferInfoHackathonSupportViewModel.Companion.ON_FAILED_OVERLAPPING
import com.innosync.hook.feature.jopoffer.info.hackathon.support.JobOfferInfoHackathonSupportViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobOfferInfoHackathonSupportFragment: BaseFragment<FragmentJobOfferIntoHackathonSupportBinding, JobOfferInfoHackathonSupportViewModel>() {

    private val data: JobOfferInfoHackathonSupportFragmentArgs by navArgs()

    override val viewModel: JobOfferInfoHackathonSupportViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPLETE -> {
                    with(mBinding) {
                        if (nameEditText.text.isNullOrBlank()) {
                            viewModel.notTouch()
                            return@bindingViewEvent
                        }
                        if (contactEditText.text.isNullOrBlank()) {
                            viewModel.notTouch()
                            return@bindingViewEvent
                        }
                        if (selfIntroductionEditText.text.isNullOrBlank()) {
                            viewModel.notTouch()
                            return@bindingViewEvent
                        }
                        viewModel.createHackathon(
                            id = data.id,
                            name = nameEditText.text.toString().replace(" ", ""),
                            contact = contactEditText.text.toString().replace(" ", ""),
                            introduction = selfIntroduction.text.toString().replace(" ", ""),
                            portfolioLink = if (portfolioEditText.text.isNullOrBlank()) null else portfolioEditText.text.toString().replace(" ", "")
                        )

                    }
                }
                ON_CLICK_BACK -> {
                    findNavController().popBackStack()
                }
                ON_SUCCESS -> {
                    requireContext().shortToast("지원에 성공하였습니다.")
                    findNavController().popBackStack()
                }
                ON_FAILED -> {
                    requireContext().shortToast("지원에 실패하였습니다.")
                }
                ON_FAILED_OVERLAPPING -> {
                    requireContext().shortToast("이미 지원한 게시글입니다.")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
    }
}