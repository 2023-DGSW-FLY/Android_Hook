package com.innosync.hook.feature.profile


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentProfileBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.loading.LoadingDialog
import com.innosync.hook.feature.profile.ProfileViewModel.Companion.ON_CLICK_CORRECTION
import com.innosync.hook.feature.profile.ProfileViewModel.Companion.ON_FAILURE
import com.innosync.hook.feature.profile.ProfileViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.toImageUrl
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment:BaseFragment<FragmentProfileBinding, ProfileViewModel>() {
    override val viewModel: ProfileViewModel by viewModels()

    private var loadingDialog: LoadingDialog? = null

    override fun onStart() {

        super.onStart()
        observeData()
        viewModel.loadInfo()

    }

    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(ChatFragment.TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
    }

    override fun observerViewModel() {
        Log.d("TAG", "observerViewModel: profile")

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(requireContext())
        }
        loadingDialog!!.show()

        bindingViewEvent { event ->
            when(event){
                ON_CLICK_CORRECTION ->{
                    Log.d("TAG", "observerViewModel: ")
                    findNavController().navigate(
                        R.id.action_profile_to_profileFix
                    )
                }

                ON_SUCCESS->{
                    if (loadingDialog!=null && loadingDialog!!.isShowing){
                        loadingDialog!!.dismiss()
                    }
                }

                ON_FAILURE ->{

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
                Glide.with(this@ProfileFragment)
                    .load(it.id.toString().toImageUrl())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(profileImage)
                Log.d("TAG", "observeData: ${it.id.toString().toImageUrl()}")
            }
        }
    }
}