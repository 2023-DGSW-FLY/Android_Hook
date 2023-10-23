package com.innosync.hook.feature.joboffermake.food

import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferMakeFoodBinding
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel.Companion.ON_CLICK_CHECKBOX
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel.Companion.ON_CLICK_COMPLETE
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel.Companion.ON_FAILED
import com.innosync.hook.feature.joboffermake.food.JobOfferMakeFoodViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JobOfferMakeFoodFragment: BaseFragment<FragmentJobOfferMakeFoodBinding, JobOfferMakeFoodViewModel>() {

    override val viewModel: JobOfferMakeFoodViewModel by viewModels()
    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {
                ON_CLICK_COMPLETE -> {
                    val context = requireContext()
                    with(mBinding) {
                        if (foodNameDetail.text.isNullOrBlank() && viewModel.checkState.value.not()) {
                            context.shortToast("음식을 입력해주세요")
                            return@bindingViewEvent
                        }
                        if (locationDetail.text.isNullOrBlank()) {
                            context.shortToast("음식을 먹을 장소를 입력해주세요")
                            return@bindingViewEvent
                        }
                        if (titleDetail.text.isNullOrBlank()) {
                            context.shortToast("제목을 입력해주세요")
                            return@bindingViewEvent
                        }
                        if (explanationDetail.text.isNullOrBlank()) {
                            context.shortToast("설명을 입력해주세요")
                            return@bindingViewEvent
                        }
                        viewModel.createFood(
                            foodName = if (viewModel.checkState.value) null else foodName.text.toString(),
                            title = titleDetail.text.toString(),
                            content = explanationDetail.text.toString(),
                            place = locationDetail.text.toString()
                        )
                    }
                }
                ON_CLICK_CHECKBOX -> {
                    if (viewModel.checkState.value) {
                        mBinding.checkbox.setImageResource(R.drawable.ic_checkbox)
                        mBinding.textNextLater.setTextColor(R.color.signitureBlue.toInt())
                    } else {
                        mBinding.checkbox.setImageResource(R.drawable.ic_not_checkbox)
                        mBinding.textNextLater.setTextColor(R.color.gray.toInt())
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
//                    findNavController().popBackStack()
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