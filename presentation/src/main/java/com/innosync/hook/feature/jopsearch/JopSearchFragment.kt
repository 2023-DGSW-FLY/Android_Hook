package com.innosync.hook.feature.jopsearch

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.innosync.data.repository.model.JobOfferModel
import com.innosync.data.repository.model.JobSearchModel
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJopSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JopSearchFragment: BaseFragment<FragmentJopSearchBinding, JopSearchViewModel>() {
    override val viewModel: JopSearchViewModel by viewModels()
    val mData = mutableListOf<JobSearchModel>()

    val TAG : String = "태그"

    override fun observerViewModel() {
        addData()
        bindingViewEvent { event ->
            when(event){
                 event-> {
                    Log.d(TAG, "observerViewModel: ${event}")
                }
            }
        }
        mBinding.jobSearchRv.adapter = JobSearchAdapter(mData)
        mBinding.jobSearchRv.layoutManager = LinearLayoutManager(requireContext())
    }

    fun addData(){
        for (i in 1..100){
            mData.add(
                JobSearchModel(
                    "한준혁",
                    "11:11",
                    "안드로이드 잘함"
                )
            )
        }
    }

}