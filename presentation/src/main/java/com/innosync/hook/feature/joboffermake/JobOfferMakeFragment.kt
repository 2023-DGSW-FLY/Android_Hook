package com.innosync.hook.feature.joboffermake

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.innosync.hook.MainActivity
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferMakeBinding
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_CLICK_COMPLETE
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_FAILED
import com.innosync.hook.feature.joboffermake.JobOfferMakeViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import java.util.Collections.checkedList

@AndroidEntryPoint
class JobOfferMakeFragment :BaseFragment<FragmentJobOfferMakeBinding, JobOfferMakeViewModel>() {
    override val viewModel: JobOfferMakeViewModel by viewModels()



    override fun observerViewModel() {
//        TODO("Not yet implemented")
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPLETE -> {
                    val check = checkedList()
                    val context = requireContext()
                    if (check.size == 0) {
                        context.shortToast("모집하는 관련 기술을 선택하세요")
                        viewModel.failedComplete()
                        return@bindingViewEvent
                    }
                    if (mBinding.competitionEditText.text.isNullOrBlank()) {
                        context.shortToast("대회 제목을 서술해주세요. ")
                        viewModel.failedComplete()
                        return@bindingViewEvent
                    }
                    if (mBinding.explanationEditText.text.isNullOrBlank()) {
                        context.shortToast("대회에 대한 내용을 서술해주세요")
                        viewModel.failedComplete()
                        return@bindingViewEvent
                    }
                    if (mBinding.competitionLinkEditText.text.isNullOrBlank()) {
                        context.shortToast("대회와 관련된 링크를 적어주세요")
                        viewModel.failedComplete()
                        return@bindingViewEvent
                    }
                    if (mBinding.competitionLinkEditText.text.toString().contains("https://").not()) {
                        context.shortToast("대회와 관련된 유효한 링크를 적어주세요")
                        viewModel.failedComplete()
                        return@bindingViewEvent
                    }
                    viewModel.insertData(
                        title = mBinding.competitionEditText.text.toString(),
                        content = mBinding.explanationEditText.text.toString(),
                        stack = check,
                        url = mBinding.competitionLinkEditText.text.toString()
                    )


                }
                ON_CLICK_BACK -> {
                    findNavController().popBackStack()
                }

                ON_SUCCESS -> {
                    requireContext().shortToast("게시물 등록에 성공했어요!")
                    findNavController().popBackStack()
                }
                ON_FAILED -> {
                    requireContext().shortToast("게시물 등록에 실패했어요")
                }

            }

        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).moveHome()
    }

    fun checkedList(): MutableList<String> {
        val checkList = mutableListOf<String>()
        with(mBinding) {
            if (androidBtn.isChecked) { checkList.add("안드로이드") }
            if (embeddedBtn.isChecked) { checkList.add("임베디드") }
            if (serverBtn.isChecked) { checkList.add("서버") }
            if (gameBtn.isChecked) { checkList.add("게임") }
            if (etcBtn.isChecked) { checkList.add("기타") }
        }
        return checkList
    }
}