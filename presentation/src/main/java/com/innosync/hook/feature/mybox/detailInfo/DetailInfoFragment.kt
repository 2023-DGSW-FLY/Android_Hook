package com.innosync.hook.feature.mybox.detailInfo

import android.text.Editable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentDetailInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailInfoFragment : BaseFragment<FragmentDetailInfoBinding, DetailnfoViewModel>() {


    override val viewModel: DetailnfoViewModel by viewModels()
    private val data: DetailInfoFragmentArgs by navArgs()

    override fun observerViewModel() {
        mBinding.nameEditText.text = data.applicantName
        mBinding.memberNumberEditText.text = data.studentId
        mBinding.memberCallEditText.text = data.contact
        mBinding.selfIntroductionEditText.text = data.introduction
        mBinding.portfolioEditText.setText(data.portfolioLink)


    }

    private fun observeState() {
//        collectLatestStateFlow(viewModel.rvData) {
//            val adapter = ApplicantsAdapter(it) {
////                findNavController().navigate()
//                //네비게이션 값 넘기기
//
//            }
//            mBinding.applicantsRecyclerView.adapter = adapter
//        }
    }
}