package com.innosync.hook.feature.profile

import android.util.Log
import androidx.fragment.app.viewModels
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentProfileBinding
import com.innosync.hook.feature.profile.ProfileViewModel.Companion.ON_CLICK_CORRECTION
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class ProfileFragment:BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()

    override fun observerViewModel() {
        observeData()
        viewModel.loadInfo()
        bindingViewEvent { event ->
            when(event){
                ON_CLICK_CORRECTION ->{
                    Log.d("태그", "observerViewModel: 수정하기 버튼")
                }
            }
        }
    }

    private fun observeData(){
        viewModel.profileData.observe(this@ProfileFragment){
            with(mBinding){
                email.text = it.email
                userName.text = it.userName
                nickname.text = it.userAccount
                portfolioUrl.text = it.portfolioURL
                githubUrl.text = it.githubURL
                userInfo.text = it.userInfo
            }
        }
    }
}