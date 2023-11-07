package com.innosync.hook.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentSettingBinding
import com.innosync.hook.feature.mybox.MyBoxViewModel
import com.innosync.hook.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {


    override val viewModel: SettingViewModel by viewModels()
    override fun observerViewModel() {
        observeState()
        viewModel.load()
    }

    override fun onStart() {
        super.onStart()
        mBinding.switchNotification.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setNotification(b)
        }
    }

    private fun observeState() {
        collectLatestStateFlow(viewModel.loadState) {
            mBinding.layoutSetting.visibility = if(it) View.VISIBLE else View.GONE
        }
        collectLatestStateFlow(viewModel.settingState) {
            mBinding.switchNotification.isChecked = it
        }
    }


}
