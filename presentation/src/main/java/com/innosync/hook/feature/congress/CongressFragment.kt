package com.innosync.hook.feature.congress

import android.content.Intent
import android.net.Uri
import android.widget.GridLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentCongressBinding
import com.innosync.hook.feature.congress.CongressViewModel.Companion.ON_ERROR_LOAD
import com.innosync.hook.util.RecyclerViewDecoration
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CongressFragment: BaseFragment<FragmentCongressBinding, CongressViewModel>() {
    override val viewModel: CongressViewModel by viewModels()

    override fun observerViewModel() {
        observerLiveData()
        viewModel.loadData()

        bindingViewEvent {  event ->
            when(event) {
                ON_ERROR_LOAD -> {
                    context?.shortToast("대회 정보 로딩에 실패하였습니다.")
                }
            }
        }
    }

    private fun observerLiveData() {
        with(viewModel) {
            congressData.observe(this@CongressFragment) {
                mBinding.rvCongress.layoutManager = GridLayoutManager(requireContext(), 2)
                mBinding.rvCongress.adapter = CongressAdaptor(
                    it,
                    requireContext()
                ) { model ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data =  Uri.parse(model.url)
                    requireContext().startActivity(intent)
                }
                mBinding.rvCongress.addItemDecoration(
                    RecyclerViewDecoration(2, 10)
                )
            }
        }
    }


}