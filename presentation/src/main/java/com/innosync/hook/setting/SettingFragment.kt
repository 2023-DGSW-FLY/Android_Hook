package com.innosync.hook.setting

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentSettingBinding
import com.innosync.hook.feature.auth.AuthActivity
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.mybox.MyBoxViewModel
import com.innosync.hook.setting.SettingViewModel.Companion.ON_CLICK_LOGOUT
import com.innosync.hook.setting.SettingViewModel.Companion.ON_CLICK_NOTICE
import com.innosync.hook.util.collectLatestStateFlow
import com.innosync.hook.util.shortToast
import com.innosync.hook.util.startActivityWithFinishAll
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {


    override val viewModel: SettingViewModel by viewModels()
    override fun observerViewModel() {
        observeState()
        viewModel.load()

        bindingViewEvent {
            when(it) {
                ON_CLICK_NOTICE -> {
                    requireContext().shortToast("등록된 공지사항이 없습니다.")
                }
                ON_CLICK_LOGOUT -> {
                    viewModel.logout()
                }
            }
        }
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

        collectLatestStateFlow(viewModel.logoutState) {
            if (it) {
                startActivityWithFinishAll(AuthActivity::class.java)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_myBox) {
            Log.d(ChatFragment.TAG, "onResume: ddd")
            mainActivity.moveMyBox()
        }
    }


}
