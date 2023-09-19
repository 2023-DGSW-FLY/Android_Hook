package com.innosync.hook.feature.congress

import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentCongressBinding
import com.innosync.hook.util.RecyclerViewDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CongressFragment: BaseFragment<FragmentCongressBinding, CongressViewModel>() {
    override val viewModel: CongressViewModel by viewModels()

    override fun observerViewModel() {
        observerLiveData()
        viewModel.loadData()
    }

    private fun observerLiveData() {
        with(viewModel) {
            congressData.observe(this@CongressFragment) {
                mBinding.rvCongress.layoutManager = GridLayoutManager(requireContext(), 2)
                mBinding.rvCongress.adapter = CongressAdaptor(
                    it,
                    requireContext()
                )
                mBinding.rvCongress.addItemDecoration(
                    RecyclerViewDecoration(2, 10)
                )
            }
        }
    }


}