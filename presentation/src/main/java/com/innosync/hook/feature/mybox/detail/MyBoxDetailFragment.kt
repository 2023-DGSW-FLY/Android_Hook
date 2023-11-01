package com.innosync.hook.feature.mybox.detail

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentMyBoxDetailBinding
import com.innosync.hook.util.ItemSpacingDecoration
import com.innosync.hook.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyBoxDetailFragment: BaseFragment<FragmentMyBoxDetailBinding, MyBoxDetailViewModel>() {

    override val viewModel: MyBoxDetailViewModel by viewModels()

    override fun observerViewModel() {
        val dummy = 3
        viewModel.loadData(dummy)
        observeState()


        observeData()
        viewModel.myPostDataLoadInfo(dummy)
    }

    private fun observeState(){
        collectLatestStateFlow(viewModel.rvData){
            val adapter = ApplicantsAdapter(it) {
//                findNavController().navigate()
                //네비게이션 값 넘기기

            }
            mBinding.applicantsRecyclerView.adapter = adapter
        }
    }

    private fun observeData() {
        viewModel.myPostData.observe(this@MyBoxDetailFragment) {
            with(mBinding) {
//                title.text = it.title
                textTitle.visibility = View.GONE
                userName.text = it.username
                nickName.text = it.writer
                reallyTechnology.text = it.stack.toStacks()
                matching.text = it.status
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
        mBinding.applicantsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.applicantsRecyclerView.addItemDecoration(ItemSpacingDecoration(10))
    }



}