package com.innosync.hook.feature.joboffermake.exercise

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferMakeExerciseBinding
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.joboffermake.exercise.JobOfferMakeExerciseViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.joboffermake.exercise.JobOfferMakeExerciseViewModel.Companion.ON_CLICK_COMPLETE
import com.innosync.hook.feature.joboffermake.exercise.JobOfferMakeExerciseViewModel.Companion.ON_FAILED
import com.innosync.hook.feature.joboffermake.exercise.JobOfferMakeExerciseViewModel.Companion.ON_SUCCESS
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class JobOfferMakeExerciseFragment: BaseFragment<FragmentJobOfferMakeExerciseBinding, JobOfferMakeExerciseViewModel>() {

    override val viewModel: JobOfferMakeExerciseViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPLETE -> {
                    with(mBinding) {
                        val context = requireContext()
                        if (explanationDetail.text.isNullOrBlank()) {
                            context.shortToast("진행할 운동을 입력하세요.")
                            return@bindingViewEvent
                        }
                        if (datetimeDetail.text.isNullOrBlank()) {
                            context.shortToast("시간을 입력하세요.")
                            return@bindingViewEvent
                        }
                        if (locationDetail.text.isNullOrBlank()) {
                            context.shortToast("장소를 입력하세요.")
                            return@bindingViewEvent
                        }
                        if (titleDetail.text.isNullOrBlank()) {
                            context.shortToast("제목을 입력하세요.")
                            return@bindingViewEvent
                        }
                        if (explanationDetail.text.isNullOrBlank()) {
                            context.shortToast("설명을 입력하세요.")
                            return@bindingViewEvent
                        }
                        viewModel.createExercise(
                            title = titleDetail.text.toString(),
                            content = explanationDetail.text.toString(),
                            place = locationDetail.text.toString(),
                            dateTime = datetimeDetail.text.toString(),
                            exercise = exercise.text.toString()
                        )

                    }
                }
                ON_CLICK_BACK -> {
                    findNavController().popBackStack()
                }
                ON_SUCCESS -> {
                    requireContext().shortToast("게시글 작성에 성공하였습니다.")
                    findNavController().popBackStack()
                }
                ON_FAILED -> {
                    requireContext().shortToast("게시글 작성에 실패하였습니다.")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).moveHome()
    }


}