package com.innosync.hook.feature.profile.fix

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.innosync.domain.model.UserModel
import com.innosync.hook.MainActivity
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentProfileFixBinding
import com.innosync.hook.feature.chat.ChatFragment
import com.innosync.hook.feature.loading.LoadingDialog
import com.innosync.hook.feature.profile.fix.ProfileFixViewModel.Companion.ON_CLICK_BACK
import com.innosync.hook.feature.profile.fix.ProfileFixViewModel.Companion.ON_CLICK_COMPLETE
import com.innosync.hook.feature.profile.fix.ProfileFixViewModel.Companion.ON_CLICK_IMAGE
import com.innosync.hook.feature.profile.fix.ProfileFixViewModel.Companion.ON_FAILURE
import com.innosync.hook.feature.profile.fix.ProfileFixViewModel.Companion.ON_SUCCESS
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class ProfileFixFragment : BaseFragment<FragmentProfileFixBinding, ProfileFixViewModel>() {
    override val viewModel: ProfileFixViewModel by viewModels()

    private var profileImage: Bitmap? = null
    private var loadingDialog: LoadingDialog? = null

    override fun observerViewModel() {
        viewModel.loadInfo()

        observeData()
        bindingViewEvent { event ->
            when (event) {

                ON_CLICK_IMAGE -> {
                    startGallery()
                }

                ON_CLICK_COMPLETE -> {
                    Log.d("TAG", "observerViewModel: ")
                    with(mBinding) {
                        val context = requireContext()
                        if (userName.text.isNullOrBlank()) {
                            context.shortToast("이름이 존재하지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                        if (nickname.text.isNullOrBlank()) {
                            context.shortToast("닉네임이 존재하지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }

                        if (email.text.isNullOrBlank()) {
                            context.shortToast("이메일이 존재하지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                        if (githubUrl.text.isNullOrBlank()) {
                            context.shortToast("깃허브 주소가 존재하지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                        if (githubUrl.text.contains("https://github.com/").not()) {
                            context.shortToast("깃허브 주소가 올바르지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                        if (portfolioUrl.text.isNullOrBlank()) {
                            context.shortToast("포트폴리오 주소가 존재하지 않습니다.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                        if (userInfo.text.isNullOrBlank()) {
                            context.shortToast("자기소개서를 작성해주세요.")
                            viewModel.failuresBtn()
                            return@bindingViewEvent
                        }
                    }
                    viewModel.fix(
                        userAccount = mBinding.nickname.text.toString().replace("", ""),
                        userName = mBinding.userName.text.toString().replace("", ""),
                        email = mBinding.email.text.toString().replace("", ""),
                        userInfo = mBinding.userInfo.text.toString().replace("", ""),
                        githubURL = mBinding.githubUrl.text.toString().replace("", ""),
                        portfolioURL = mBinding.portfolioUrl.text.toString().replace("", ""),
                        profileImage = profileImage
                    )


                    if (loadingDialog == null){
                        loadingDialog = LoadingDialog(requireContext())
                    }
                    loadingDialog!!.show()

                }


                ON_SUCCESS -> {
                    Log.d("TAG", "observerViewModel: 성공")
                    requireContext().shortToast("프로필 수정에 성공하였습니다")
                    if (loadingDialog != null && loadingDialog!!.isShowing){
                        loadingDialog!!.dismiss()
                    }
                    findNavController().popBackStack()

                }

                ON_FAILURE ->{
                    Log.d("TAG", "observerViewModel:  실패")
                    if (loadingDialog != null && loadingDialog!!.isShowing) {
                        loadingDialog!!.dismiss()
                    }
                    viewModel.failuresBtn()
                }
                ON_CLICK_BACK ->{
                    findNavController().popBackStack()
                }

            }
        }
    }


    override fun onResume() {
        super.onResume()
        val mainActivity = (requireActivity() as MainActivity)
        if (mainActivity.nowSelectItem() != R.id.nav_item_home) {
            Log.d(ChatFragment.TAG, "onResume: ddd")
            mainActivity.moveHome()
        }
    }



    private fun startGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            "image/*"
        )
        startForResult.launch(intent)
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data ?: return@registerForActivityResult
            profileImage = try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)
                } else {
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return@registerForActivityResult
            }
            setImage(url = null, uri = uri)
            Log.e("ProfileFixFragment", "${File(uri.path!!)}")
        }
    }

    private fun setImage(url: String?, uri: Uri?) {
        Glide.with(requireContext())
            .load(url ?: uri)
            .error(R.drawable.ic_not_image)
            .placeholder(R.drawable.ic_not_image)
            .centerCrop()
            .into(mBinding.profileImage)
    }
    private fun observeData(){
        viewModel.profileFixData.observe(this@ProfileFixFragment){
            with(mBinding){
                userName.setText(it.userName)
                nickname.text = it.userAccount
                email.setText(it.email)
                githubUrl.setText(it.githubURL)
                portfolioUrl.setText(it.portfolioURL)
                userInfo.setText(it.userInfo)

            }
        }
    }

}
