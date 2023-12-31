package com.innosync.hook.feature.mybox.detail

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMyBoxDetailBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.jopoffer.model.JobOfferModel
import com.innosync.hook.feature.mybox.detail.MyBoxDetailViewModel.Companion.ON_CLICK_SETTING
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.removeItemDecorations
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyBoxDetailFragment: BaseFragment<FragmentMyBoxDetailBinding, MyBoxDetailViewModel>() {

    override val viewModel: MyBoxDetailViewModel by viewModels()
    private val data: MyBoxDetailFragmentArgs by navArgs()

    override fun observerViewModel() {
        observeData()
        bindingViewEvent {
            when (it) {
                ON_CLICK_SETTING -> {
                    findNavController().navigate(R.id.action_myBoxDetailFragment_to_settingFragment)
                }
            }
        }
    }

    private fun observeState(){
        collectLatestStateFlow(viewModel.rvData){
            val adapter = ApplicantsAdapter(
                itemList = it,
                action = {

                    findNavController().navigate(
                        MyBoxDetailFragmentDirections.actionMyBoxDetailFragmentToDetailInfoFragment(
                            it.name, it.studentId, it.contact,it.introduction, it.portfolioLink?: "" ,it.userId
                        )
                    )
                }
            )

//                findNavController().navigate()
                //네비게이션 값 넘기기

            mBinding.applicantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            mBinding.applicantsRecyclerView.removeItemDecorations()
            mBinding.applicantsRecyclerView.addItemDecoration(ItemSpacingDecoration(10))
            mBinding.applicantsRecyclerView.adapter = adapter
        }
    }

    private fun observeData() {
        viewModel.myPostData.observe(this@MyBoxDetailFragment) {
            with(mBinding) {
//                title.text = it.title
//                textTitle.visibility = View.GONE
                userName.text = it.username
                nickName.text = it.writer
                reallyTechnology.text = it.stack.toStacks()
                matching.text = when(it.status) {
                    "matching" -> "매칭중"
                    else -> "매칭완료"
                }
//                android:id="@+id/textTitle"
                textTitle.text  = it.title
                Glide.with(mBinding.profileImg)
                    .load(it.userId.toString().toImageUrl())
                    .into(mBinding.profileImg)
            }
        }
    }



    private fun List<String>.toStacks(): String {
        var result = ""
        for (i in this) {
            result += i
        }
        return result
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData(data.id)
        viewModel.myPostDataLoadInfo(data.id)
        observeState()
        mBinding.applicantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.applicantsRecyclerView.removeItemDecorations()
        mBinding.applicantsRecyclerView.addItemDecoration(ItemSpacingDecoration(10))
        mBinding.backBtn.setOnClickListener{
            findNavController().popBackStack()
        }

        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_myBox) {
            Log.d(ChatFragment.TAG, "onResume: ddd")
            mainActivity.moveMyBox()
        }
    }
}