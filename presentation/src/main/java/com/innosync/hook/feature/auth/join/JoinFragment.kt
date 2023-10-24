package com.innosync.hook.feature.auth.join

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
import com.innosync.hook.R
import com.innosync.hook.base.BaseFragment
import com.innosync.hook.databinding.FragmentJoinBinding
import com.innosync.hook.feature.auth.join.JoinViewModel.Companion.ON_CLICK_JOIN
import com.innosync.hook.feature.auth.join.JoinViewModel.Companion.ON_CLICK_UPLOAD
import com.innosync.hook.feature.auth.join.JoinViewModel.Companion.ON_FAILURE
import com.innosync.hook.feature.auth.join.JoinViewModel.Companion.ON_SUCCESS
import com.innosync.hook.feature.loading.LoadingDialog
import com.innosync.hook.util.getRealPathFromURI
import com.innosync.hook.util.shortToast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding, JoinViewModel>() {

    override val viewModel: JoinViewModel by viewModels()

    private var profileImage: Bitmap? = null
    private var loadingDialog: LoadingDialog? = null

    override fun observerViewModel() {
        bindingViewEvent {  event ->
            when(event) {
                ON_CLICK_JOIN -> {
                    with(mBinding) {
                        val context = requireContext()
                        if (editId.text.isNullOrBlank()) {
                            context.shortToast("아이디가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editPassword.text.isNullOrBlank()) {
                            context.shortToast("비밀번호가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editName.text.isNullOrBlank()) {
                            context.shortToast("이름이 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editEmail.text.isNullOrBlank()) {
                            context.shortToast("이메일이 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editGithub.text.isNullOrBlank()) {
                            context.shortToast("깃허브 주소가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editGithub.text.contains("https://github.com/").not()) {
                            context.shortToast("깃허브 주소가 올바르지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (editPortfolio.text.isNullOrBlank()) {
                            context.shortToast("포트폴리오 주소가 존재하지 않습니다.")
                            return@bindingViewEvent
                        }
                        if (profileImage == null) {
                            context.shortToast("프로필 이미지를 등록해주세요.")
                            return@bindingViewEvent
                        }
                        viewModel.join(
                            userAccount = editId.text.toString().replace(" ", ""),
                            password = editPassword.text.toString().replace(" ", ""),
                            userName = editName.text.toString().replace(" ", ""),
                            email = editEmail.text.toString().replace(" ", ""),
                            userInfo = "".replace(" ", ""),
                            githubURL = editGithub.text.toString().replace(" ", ""),
                            portfolioURL = editPortfolio.text.toString().replace(" ", ""),
                            profileImage = profileImage!!
                        )
                        if (loadingDialog == null) {
                            loadingDialog = LoadingDialog(requireContext())
                        }
                        loadingDialog!!.show()

                    }
                }
                ON_SUCCESS -> {
                    requireContext().shortToast("회원가입에 성공하였습니다.")
                    if (loadingDialog != null && loadingDialog!!.isShowing) {
                        loadingDialog!!.dismiss()
                    }
                    findNavController().popBackStack()
                }
                ON_FAILURE -> {
                    requireContext().shortToast("회원가입에 실패하였습니다.")
                    if (loadingDialog != null && loadingDialog!!.isShowing) {
                        loadingDialog!!.dismiss()
                    }
                }
                ON_CLICK_UPLOAD -> {
                    startGallery()
                }

            }
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

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data?: return@registerForActivityResult//.getRealPathFromURI(requireContext())!!
            profileImage = try{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, uri))
                } else {
                    MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return@registerForActivityResult
            }
            setImage(url = null, uri = it.data!!.data)
//            viewModel.imageUpload(File(uri.path!!))
            Log.e("LostFoundWriteFragment", "${File(uri.path!!)}")
        }
    }
    private fun setImage(url: String?, uri: Uri?) {
        mBinding.editPortfolio
        Glide.with(mBinding.root)
            .load(url ?: uri)
            .error(R.drawable.ic_not_image)
            .centerCrop()
            .into(mBinding.imageProfile)
    }

}
