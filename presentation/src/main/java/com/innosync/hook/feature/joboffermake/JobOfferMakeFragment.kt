package com.innosync.hook.feature.joboffermake

import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJobOfferMakeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobOfferMakeFragment :BaseFragment<FragmentJobOfferMakeBinding, JobOfferMakeViewModel>() {
    override val viewModel: JobOfferMakeViewModel by viewModels()


    override fun observerViewModel() {
//        TODO("Not yet implemented")
    }
}